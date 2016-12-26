
package com.tianyalan.product.open.dao;

import com.tianyalan.product.open.entity.ThirdSupport;

public interface ThirdSupportDao {
	
	ThirdSupport getThirdSupportById(Long id);

	Long addThirdSupport(ThirdSupport thirdSupport);

	void updateThirdSupport(ThirdSupport thirdSupport);
}