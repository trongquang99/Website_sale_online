package com.website.online.sale.controller.Store;

import com.website.online.sale.dtos.request.StoreRequest;
import com.website.online.sale.dtos.response.Store.StoreListResponse;
import com.website.online.sale.service.Store.StoreListService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreListController {
    private final StoreListService storeListService;

    public StoreListController(StoreListService storeListService) {
        this.storeListService = storeListService;
    }

    @GetMapping("/api/v1/store")
    public List<StoreListResponse> getStoreList(@ParameterObject StoreRequest storeRequest){
        List<StoreListResponse> response = storeListService.getAllStoreList(storeRequest);
        return response;
    }
}
