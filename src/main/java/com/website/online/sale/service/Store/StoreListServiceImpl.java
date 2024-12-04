package com.website.online.sale.service.Store;

import com.website.online.sale.dtos.request.StoreRequest;
import com.website.online.sale.dtos.response.Store.StoreListResponse;
import com.website.online.sale.model.Cuahang;
import com.website.online.sale.repository.store.CuahangRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoreListServiceImpl implements StoreListService {
    private final CuahangRepository cuahangRepository;
    private final StoreServiceCustom storeServiceCustom;

    public StoreListServiceImpl(CuahangRepository cuahangRepository, StoreServiceCustom storeServiceCustom) {
        this.cuahangRepository = cuahangRepository;
        this.storeServiceCustom = storeServiceCustom;
    }

    @Override
    public List<StoreListResponse> getAllStoreList(StoreRequest storeRequest) {
        List<StoreListResponse> storeListResponses = new ArrayList<>();
        List<Cuahang> cuahangs = storeServiceCustom.getListStoreByFilter(storeRequest);
//        List<Cuahang> cuahangs = cuahangRepository.findAll();
        cuahangs.forEach(cuahang -> {
            StoreListResponse storeListResponse = new StoreListResponse();
            storeListResponse.setId(cuahang.getId());
            storeListResponse.setName(cuahang.getTen());
            storeListResponse.setAddress(cuahang.getDiaChi());
            storeListResponse.setPhone(cuahang.getSdt());

            storeListResponses.add(storeListResponse);
        });
        return storeListResponses;
    }
}
