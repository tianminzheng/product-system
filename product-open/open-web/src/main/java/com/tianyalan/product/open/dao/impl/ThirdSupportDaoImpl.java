package com.tianyalan.product.open.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianyalan.product.mybatis.dao.MyBatisDAO;
import com.tianyalan.product.open.dao.ThirdSupportDao;
import com.tianyalan.product.open.entity.ThirdSupport;

@Repository("thirdSupportDao")
public class ThirdSupportDaoImpl implements ThirdSupportDao {

	@Autowired
	private MyBatisDAO myBatisDAO;
	
	public ThirdSupport getThirdSupportById(Long id) {
		return (ThirdSupport) myBatisDAO.findForObject("getThirdSupportById", id);
	}

	public Long addThirdSupport(ThirdSupport thirdSupport) {
		myBatisDAO.insert("addThirdSupport", thirdSupport);

		return thirdSupport.getId();
	}

	public void updateThirdSupport(ThirdSupport thirdSupport) {
		myBatisDAO.update("updateThirdSupport", thirdSupport);
	}
}
