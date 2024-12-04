package com.website.online.sale.service.Store;

import com.website.online.sale.dtos.request.StoreRequest;
import com.website.online.sale.dtos.response.Store.StoreListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreListService {
    List<StoreListResponse> getAllStoreList(StoreRequest storeRequest);
}
