package com.tianyalan.product.foundation.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianyalan.product.foundation.util.Validators;

public class StringUtil {

	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	
	/**
	 * 字符串转换成double 数
	 * @param strDouble
	 * @return
	 */
	public static double toDouble(String strDouble){
		if(strDouble==null || strDouble.trim().length()==0){
			return 0d;
		}
		
		try{
			return Double.parseDouble(strDouble);
		}catch(Exception e){
			return 0d;
		}
	}
	
	/**
	 * 字符串转换成int 数
	 * @param strInt
	 * @return
	 */
	public static int toInt(String strInt){
		if(strInt==null || strInt.trim().length()==0){
			return 0;
		}
		
		try{
			return Integer.parseInt(strInt);
		}catch(Exception e){
			return 0;
		}
	}
	/**
	 * 把指定的数据转化为16进制格式的字符串
	 * 
	 * @param data
	 *            待转化的数据
	 * @return 16进制表示的数据
	 */
	public static String toHexString(byte[] data) {

		return toHexString(data, 0, data.length);
	}

	/**
	 * 把指定的数据转化为16进制格式的字符串， 如toHexString({8,9,12,4},1,3) = "090C"
	 * 
	 * @param data
	 *            待转化的数据
	 * @param beginIndex
	 *            需转化的数据的起始索引
	 * @param endIndex
	 *            需转化的数据的结束索引
	 * @return 16进制表示的数据
	 */
	public static String toHexString(byte[] data, int beginIndex, int endIndex) {

		if (data == null || beginIndex < 0)
			return null;
		StringBuilder strBuilder = new StringBuilder();
		for (int i = beginIndex; i < endIndex; i++) {
			strBuilder.append(hexDigits[data[i] >>> 4 & 0xf]);
			strBuilder.append(hexDigits[data[i] & 0xf]);
		}
		return strBuilder.toString();
	}

	/**
	 * 返回16进制的MDS加密串
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return
	 */
	public static String md5Encrypt(String str) {

		if (Validators.isEmpty(str)) {
			return null;
		}

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();

			return toHexString(messageDigest.digest(str.getBytes("UTF-8")));

		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException",e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			logger.error("UnsupportedEncodingException",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取一个字符串的拼音码
	 * 
	 * @param oriStr
	 *            字符串
	 * @return
	 */
	public static String getFirstLetter(String oriStr) {

		StringBuilder builder = new StringBuilder();
		char[] arr = oriStr.toCharArray();
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(
							arr[i], format);
					if (pinyin != null) {
						builder.append(pinyin[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
			else {
				builder.append(arr[i]);
			}
		}
		return builder.toString().trim();
	}

	/**
	 * 字符串处理，如果字符串为空，则返加null,否则返回 str.trim()
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String trimNull(String str) {

		if (Validators.isEmpty(str)) {
			return null;
		}
		return str.trim();
	}

	/**
	 * 使用<code>MessageFormat</code>格式化字符串.
	 * 
	 * @param message
	 *            要格式化的字符串
	 * @param params
	 *            参数表
	 * 
	 * @return 格式化的字符串，如果message为<code>null</code>，则返回<code>null</code>
	 */
	public static String formatMessage(String message, Object... params) {

		if ((Validators.isEmpty(message)) || (params == null)
				|| (params.length == 0)) {
			return message;
		}

		return MessageFormat.format(message, params);
	}
	
	public static String trim(String str) {

		return str == null ? "" : str.trim();
	}
	
	
	public static String generateLoginToken(){
		return UUID.randomUUID().toString();
	}

    public static String escapeHTML(String value) {

        if (value == null || value.length() == 0) {
            return value;
        }

        StringBuffer result = null;
        String filtered = null;
        for (int i = 0; i < value.length(); i++) {
            filtered = null;
            switch (value.charAt(i)) {
                case '<':
                    filtered = "&lt;";
                    break;
                case '>':
                    filtered = "&gt;";
                    break;
                case '&':
                    filtered = "&amp;";
                    break;
                case '"':
                    filtered = "&quot;";
                    break;
                case '\'':
                    filtered = "&#39;";
                    break;
            }

            if (result == null) {
                if (filtered != null) {
                    result = new StringBuffer(value.length() + 50);
                    if (i > 0) {
                        result.append(value.substring(0, i));
                    }
                    result.append(filtered);
                }
            } else {
                if (filtered == null) {
                    result.append(value.charAt(i));
                } else {
                    result.append(filtered);
                }
            }
        }

        return result == null ? value : result.toString();
    }
    
    /**  
     *  截取包含html标签的字符串，如果文本超过length个字符，多余的将用replaceStr显示
      * @param sourceStr :  
      *            要截取的字符串  
      * @param length :  
      *            需要显示的长度  
      * @param replaceStr :  
      *            多余的字段，将用  replaceStr代替，如(replaceStr="...")
      * @return Object  
      *   
      */  
     public static String abbreviate(String sourceStr, int length, String replaceStr) {   
         if (sourceStr == null || "".equals(sourceStr)) {   
             return "";   
        }   
        
   
      /*   int d = 0; // byte length   
         int n = 0; // char length   
         for (; n < sourceStr.length(); n++) {   
             d = (int) sourceStr.charAt(n) > 256 ? d + 2 : d + 1;   
             if (d > length) {   
                 break;   
             }   
         }   
   
        if (d > length) {   
             n = n - replaceStr.length() / 2;   
             return sourceStr.substring(0, n > 0 ? n : 0) + replaceStr;   
         }   */
         String destStr = Html2Text(sourceStr);
         if(destStr.length() > length){
        	 destStr = destStr.substring(0, length-1) + replaceStr;
         }
         
   
        return destStr;   
     }   
  
     /**  截取包含html标签的字符串 
      * @param str :  要截取的字符串
      * @return  去掉html标签后的纯文本  
      */  
    public static String Html2Text(String sourceStr) {   
        String htmlStr = sourceStr; // 含html标签的字符串   
        String textStr = "";   
        java.util.regex.Pattern p_script;   
         java.util.regex.Matcher m_script;   
         java.util.regex.Pattern p_style;   
        java.util.regex.Matcher m_style;   
         java.util.regex.Pattern p_html;   
         java.util.regex.Matcher m_html;   
   
         java.util.regex.Pattern p_html1;   
         java.util.regex.Matcher m_html1;   
   
         try {   
         	String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>   
             // }   
             String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>   
            // }   
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式   
             String regEx_html1 = "<[^>]+";   
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);   
            m_script = p_script.matcher(htmlStr);   
             htmlStr = m_script.replaceAll(""); // 过滤script标签   
  
             p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);   
             m_style = p_style.matcher(htmlStr);   
             htmlStr = m_style.replaceAll(""); // 过滤style标签   
   
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);   
             m_html = p_html.matcher(htmlStr);   
             htmlStr = m_html.replaceAll(""); // 过滤html标签   
   
             p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);   
             m_html1 = p_html1.matcher(htmlStr);   
             htmlStr = m_html1.replaceAll(""); // 过滤html标签   
             
             htmlStr = htmlStr.replaceAll("&nbsp;", "");
   
            textStr = htmlStr;   
   
         } catch (Exception e) {   
             System.err.println("Html2Text: " + e.getMessage());   
         }   
  
        return textStr;// 返回文本字符串   
    }  
    /**
     * 
     * 2013-08-05  ZengBiao   -规范化金额格式
     * 2013-07-17  Zengbiao   -Initial
     * 
     * 
     * 2个字符串（其实是数值），的和；如addAsDouble("8.00","3.00") = "11.00";
     * @param firstStr
     * @param secStr
     * @return
     */
    public static String addAsDouble(String firstStr,String secStr){
    	double resultDoubleVal = 0.0;
    	double firstDoubleVal = 0.0;
    	double secDoubleVal = 0.0;
    	String result = null;
    	if(firstStr != null && !"".equals(firstStr)){
    		firstDoubleVal = Double.parseDouble(firstStr);
    	}
    	
    	if(secStr != null && !"".equals(secStr)){
    		secDoubleVal = Double.parseDouble(secStr);
    	}
    	resultDoubleVal = firstDoubleVal + secDoubleVal;
    	result = String.valueOf(resultDoubleVal);
    	result = getUniversalFeeFormat(result);
    	return result;
    }
    /**
     * 将字符串形式的金额统一起来，目前都显示2位小数点
     * @param feeStr
     * @return
     */
    public static String getUniversalFeeFormat(String feeStr){
    	String resultStr = "";
    	if(StringUtils.isBlank(feeStr))return "";
    	
    	int dotLoc = feeStr.indexOf(".");
    	int length = feeStr.length();
    	
    	if(dotLoc == -1){
    		//没有小数点，则补充2位
    		resultStr = feeStr + ".00";
    	} else if(dotLoc == 0){
    		//没有小数点，如.2756或.1
    		resultStr = "0" + feeStr;
    		resultStr = getUniversalFeeFormat(resultStr);
    	}else if(length - dotLoc == 2){
    		//如3.4，将它转化为3.40
    		resultStr = feeStr + "0";
    	} else if(length - dotLoc == 3){
    		//如3.40，不需要改变
    		resultStr = feeStr;
    	} else if(length - dotLoc > 3){
    		//如3.14159265
    		resultStr = feeStr.substring(0, dotLoc + 3);
    	} else if(length - dotLoc == 1){
    		//如312.  较少见，补充00
    		resultStr = feeStr + "00";
    	} 
    	
    	return resultStr;
    }

    /**
     * 根据分隔符来分割数字串 example:"1-9",解析后结构就是[1,2,3,4,5,6,7,8,9], "1;2;4;5" 分割后结果就是[1,2,4,5],
     * 暂时只支持这两种格式的解析
     * @param numArea
     * @param delimiter
     * @return
     */
    public static String[] parseNumArea(String numArea) {
		String[] noArray;
		
		if (numArea.indexOf("-") > 0) {
			String[] nums = numArea.split("-");

			int first = Integer.parseInt(nums[0]);
			int last  = Integer.parseInt(nums[1]);

			noArray = new String[last - first + 1];

			for (int i = 0, j = first; j <= last; i++, j++) {
				noArray[i] = j + "";
			}
		}else{
			noArray = StringUtils.split(numArea, ";");
		}
		
		return noArray;		
	}
    
    /**
     * 根据输入的字符串，生成指定长度的随机字符串
     * @param strPool
     * @param length
     * @return
     */
    public static String randomString(String strPool,int length) {
		if (strPool==null || length < 1) {
			return null;
		}
				
		Random randGen = new Random();
		char[] numbersAndLetters = (strPool).toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(strPool.length())];
		}
		return new String(randBuffer);
	}
    
    public static boolean isDoubleString(String inputStr){
    	try{
    		Double.valueOf(inputStr);
    	}catch(Exception e){
    		return false;
    	}
    	
    	return true;
    }
    
    public static boolean isChinese(char c) { 
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 
        		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS 
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A 
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B 
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION 
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS 
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) { 
            return true; 
        }
        
        return false; 
    } 
    
    /**
     * 生成特定前缀的唯一字符串(TODO:advance strategy needed)
     * @param prefix
     * @return
     */
    public static String generateUniqueSequence(String prefix){
    	return prefix + System.nanoTime();
	}

	public static void main(String agv[]) {	
		
	    for(int i=0;i<100;i++){
	    	System.out.println(generateUniqueSequence("X"));
	    }
//		Map<String,String> weekDayDateMap = new HashMap<String,String>();
//		Calendar calendar = Calendar.getInstance();
//		for(int i=1;i<=7;i++){
//			calendar.add(Calendar.DAY_OF_MONTH, 1);	
//			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
//			if(dayOfWeek==0){
//				dayOfWeek = 7;
//			}
//			weekDayDateMap.put(dayOfWeek+"", DateUtil.formatDate("yyyyMMdd", calendar.getTime()));
//		}
//		
//		
//		String a = "SJ00000678";
//		String code = "SJ";
//		
//		System.out.println(a.substring(code.length()));
//		
//		try {
//			Class clazz = Class.forName("org.apache.ibatis.type.EnumOrdinalTypeHandler");
//			clazz.newInstance();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(isChinese('《'));
//		System.out.println(Html2Text("&nbsp;dafafwa&nbsp;"));
//		System.out.println(md5Encrypt("zhicall_rootZhp$$Admin"));
//		System.out.println(md5Encrypt("zhaolijun@zhicall.com123456"));
		System.out.println(md5Encrypt("18661086299123456"));
//		System.out.println(randomString("0123456789", 16));
		
//		Calendar today = Calendar.getInstance();
//		today.set(2014, 4, 5);
//		int dayOfWeek = today.get(Calendar.DAY_OF_WEEK)-1;
//		if(dayOfWeek==0){
//			dayOfWeek = 7;
//		}
//		System.out.println(dayOfWeek);
	}
}
