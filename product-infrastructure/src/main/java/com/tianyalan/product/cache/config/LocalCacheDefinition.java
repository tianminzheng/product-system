
package com.tianyalan.product.cache.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class LocalCacheDefinition {

	private String tableName;
	
	private String mapperSQLId;
	
	private String changeSQLId; //用于检测数据从上次同步以后的更新情况
	
	private String voCalssName;
	
	private String voPrimaryKey;
	
	private boolean needRefresh;
	
	private Class<?> classType;

		
	public LocalCacheDefinition(String tableName, String mapperSQLId,String voCalssName, String voPrimaryKey,boolean needRefresh) {
		super();
		this.tableName = tableName;
		this.mapperSQLId = mapperSQLId;
		this.voCalssName = voCalssName;
		this.voPrimaryKey = voPrimaryKey;
		this.needRefresh = needRefresh;
		try {
			this.classType = Class.forName(voCalssName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public LocalCacheDefinition(String tableName, String mapperSQLId,String checkSQLId,String voCalssName, String voPrimaryKey,boolean needRefresh) {
		this(tableName, mapperSQLId, voCalssName, voPrimaryKey, needRefresh);
		this.changeSQLId = checkSQLId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMapperSQLId() {
		return mapperSQLId;
	}

	public void setMapperSQLId(String mapperSQLId) {
		this.mapperSQLId = mapperSQLId;
	}

	public String getChangeSQLId() {
		return changeSQLId;
	}

	public void setChangeSQLId(String changeSQLId) {
		this.changeSQLId = changeSQLId;
	}

	public String getVoCalssName() {
		return voCalssName;
	}

	public void setVoCalssName(String voCalssName) {
		this.voCalssName = voCalssName;
	}

	public String getVoPrimaryKey() {
		return voPrimaryKey;
	}

	public void setVoPrimaryKey(String voPrimaryKey) {
		this.voPrimaryKey = voPrimaryKey;
	}
	
	public Class<?> getVOClassObject(){	
		return this.classType;
	}

	public boolean isNeedRefresh() {
		return needRefresh;
	}

	public void setNeedRefresh(boolean needRefresh) {
		this.needRefresh = needRefresh;
	}
}
