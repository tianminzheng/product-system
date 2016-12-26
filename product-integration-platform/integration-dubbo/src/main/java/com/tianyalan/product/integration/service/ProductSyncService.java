package com.tianyalan.product.integration.service;

import com.tianyalan.product.integration.request.ProductSyncRequest;
import com.tianyalan.product.integration.response.ProductSyncResponse;

public interface ProductSyncService {

	ProductSyncResponse productSync(ProductSyncRequest request);
}
