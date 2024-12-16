package com.website.online.sale.service.menu;

import com.website.online.sale.dtos.request.menu.WarrantyRequest;
import com.website.online.sale.dtos.response.*;
import com.website.online.sale.dtos.response.menu.EventResponse;
import com.website.online.sale.dtos.response.menu.WarrantyResponse;
import com.website.online.sale.handlers.CustomBadRequestException;
import com.website.online.sale.model.*;
import com.website.online.sale.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final BrandRepository brandRepository;
    private final DanhmucconRepository danhmucconRepository;
    private final SanPhamRepository sanPhamRepository;
    private final TopSellingRepository topSellingRepository;
    private final MenuServiceCustom menuServiceCustom;
    private final EventRepository eventRepository;

    public MenuServiceImpl(MenuRepository menuRepository, BrandRepository brandRepository, DanhmucconRepository danhmucconRepository, SanPhamRepository sanPhamRepository, TopSellingRepository topSellingRepository, MenuServiceCustom menuServiceCustom, EventRepository eventRepository) {
        this.menuRepository = menuRepository;
        this.brandRepository = brandRepository;
        this.danhmucconRepository = danhmucconRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.topSellingRepository = topSellingRepository;
        this.menuServiceCustom = menuServiceCustom;
        this.eventRepository = eventRepository;
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

    @Override
    public SubCategoryResponse getSubCategoryMenu(Long id) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        Menu menuById = menuRepository.findById(id).orElse(null);
        if (Objects.isNull(menuById)) {
            throw new CustomBadRequestException("khong tim thay danh muc");
        }
        subCategoryResponse.setId(id);

        List<BrandProminent> brandProminents = new ArrayList<>();

        List<Brand> brands = brandRepository.findByIdCategory(id);
        brands.forEach(brand -> {
            BrandProminent brandProminent = new BrandProminent();
            brandProminent.setId(brand.getId());
            brandProminent.setName(brand.getName());
            brandProminent.setIcon(brand.getIcon());

            brandProminents.add(brandProminent);
        });

        List<SubCategory> subCategoryList = new ArrayList<>();
        List<Danhmuccon> danhmuccons = danhmucconRepository.findByIdDm(id);
        danhmuccons.forEach(danhmuccon -> {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(danhmuccon.getId());
            subCategory.setName(danhmuccon.getTen());

            subCategoryList.add(subCategory);

            List<Product> productList = new ArrayList<>();
            List<SanPham> sanPhams = sanPhamRepository.findByIdDmc(danhmuccon.getId());
            sanPhams.forEach(sanPham -> {
                Product product = new Product();
                product.setId(sanPham.getId());
                product.setName(sanPham.getTen());

                productList.add(product);
            });
            subCategory.setProducts(productList);
        });

        List<SellingProduct> sellingProductList = new ArrayList<>();
        List<TopSelling> topSellings = topSellingRepository.findByIdCategory(id);
        topSellings.forEach(topSelling -> {
            SellingProduct sellingProduct = new SellingProduct();
            SanPham sanPham = sanPhamRepository.findById(topSelling.getIdProduct()).orElse(null);
            if (Objects.nonNull(sanPham)) {
                sellingProduct.setName(sanPham.getTen());
                sellingProduct.setPrice(sanPham.getGia());
                sellingProduct.setImage(sanPham.getImage());

                sellingProductList.add(sellingProduct);
            }
        });
        subCategoryResponse.setSellingProducts(sellingProductList);

        subCategoryResponse.setSubCategories(subCategoryList);
        subCategoryResponse.setBrands(brandProminents);
        return subCategoryResponse;
    }

    @Override
    public List<WarrantyResponse> getWarrantyCheck(WarrantyRequest warrantyRequest) {
        List<WarrantyResponse> warrantyResponses = new ArrayList<>();
        List<SanPham> sanPhams = menuServiceCustom.getWarrantyByFilter(warrantyRequest);
        sanPhams.forEach(sanPham -> {
            WarrantyResponse warrantyResponse = new WarrantyResponse();
            warrantyResponse.setId(sanPham.getId());
            warrantyResponse.setName(sanPham.getTen());
            warrantyResponse.setQuantity(sanPham.getSoLuong());
            warrantyResponse.setPrice(sanPham.getGia());
            warrantyResponse.setImage(sanPham.getImage());
            warrantyResponse.setDescription(sanPham.getMoTa());

            warrantyResponses.add(warrantyResponse);
        });
        return warrantyResponses;
    }

    @Override
    public List<EventResponse> getAllEvent() {
        List<EventResponse> eventResponses = new ArrayList<>();
        List<Event> events = eventRepository.findAll();
        events.forEach(event -> {
            EventResponse eventResponse = new EventResponse();
            eventResponse.setTitle(event.getTitle());
            eventResponse.setDescription(event.getDescription());
            eventResponse.setImage(event.getImage());
            eventResponse.setCreated(Date.from(event.getCreated()));

            eventResponses.add(eventResponse);
        });
        return eventResponses;
    }
}

