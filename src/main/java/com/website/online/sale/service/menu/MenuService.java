package com.website.online.sale.service.menu;

import com.website.online.sale.dtos.response.MenuResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    List<MenuResponse> getAllMenu();
}
