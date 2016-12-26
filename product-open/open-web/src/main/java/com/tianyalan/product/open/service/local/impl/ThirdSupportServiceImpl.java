package com.tianyalan.product.open.service.local.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyalan.product.open.dao.ThirdSupportDao;
import com.tianyalan.product.open.entity.ThirdSupport;
import com.tianyalan.product.open.service.local.ThirdSupportService;

@Service("thirdSupportService")
public class ThirdSupportServiceImpl implements ThirdSupportService {

	@Autowired
	private ThirdSupportDao thirdSupportDao;

	public ThirdSupport getThirdSupportById(Long id) {

		return thirdSupportDao.getThirdSupportById(id);
	}

	public Long addThirdSupport(ThirdSupport thirdSupport) {

		return thirdSupportDao.addThirdSupport(thirdSupport);
	}
	
	public void updateThirdSupport(ThirdSupport thirdSupport) {

		thirdSupportDao.updateThirdSupport(thirdSupport);
	}
}