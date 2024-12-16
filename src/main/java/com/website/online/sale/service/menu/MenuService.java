package com.website.online.sale.service.menu;

import com.website.online.sale.dtos.request.menu.WarrantyRequest;
import com.website.online.sale.dtos.response.MenuResponse;
import com.website.online.sale.dtos.response.SubCategoryResponse;
import com.website.online.sale.dtos.response.menu.EventResponse;
import com.website.online.sale.dtos.response.menu.WarrantyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuResponse> getAllMenu();

    SubCategoryResponse getSubCategoryMenu(Long id);

    List<WarrantyResponse> getWarrantyCheck(WarrantyRequest warrantyRequest);

    List<EventResponse> getAllEvent();
}
