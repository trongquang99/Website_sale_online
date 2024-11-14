package com.website.online.sale.service.menu;

import com.website.online.sale.dtos.response.MenuResponse;
import com.website.online.sale.model.Menu;
import com.website.online.sale.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuResponse> getAllMenu() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuResponse> menuResponses = new ArrayList<>();
        menus.forEach(menu -> {
            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setName(menu.getTen());
            menuResponse.setUrlIcon(menu.getIcon());
            menuResponses.add(menuResponse);
        });
        return menuResponses;
    }
}
