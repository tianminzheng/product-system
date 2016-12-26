
package com.tianyalan.product.open.service.local;

import com.tianyalan.product.open.entity.ThirdSupport;

public interface ThirdSupportService {

	ThirdSupport getThirdSupportById(Long id);

	Long addThirdSupport(ThirdSupport thirdSupport);

	void updateThirdSupport(ThirdSupport thirdSupport);
}