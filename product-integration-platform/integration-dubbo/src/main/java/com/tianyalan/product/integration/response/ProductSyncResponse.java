package com.tianyalan.product.integration.response;

import java.util.List;

public class ProductSyncResponse extends ResponseBase {
	
	private static final long serialVersionUID = 1L;
	
	private List<ProductItem> productItems;

	public List<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}
}
