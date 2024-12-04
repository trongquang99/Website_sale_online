package com.website.online.sale.controller.statistic;

import com.website.online.sale.dtos.response.statistic.CustomerStatisticResponse;
import com.website.online.sale.dtos.response.statistic.ProductStatisticResponse;
import com.website.online.sale.service.statistic.CustomerStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {
    private final CustomerStatisticService customerStatisticService;

    public StatisticController(CustomerStatisticService customerStatisticService) {
        this.customerStatisticService = customerStatisticService;
    }

    @GetMapping("/api/v1/customer_statistic")
    public List<CustomerStatisticResponse> getCustomerStatistic() {
        List<CustomerStatisticResponse> response = customerStatisticService.getCustomerStatistic();
        return response;
    }

    @GetMapping("/api/v1/product_statistic")
    public List<ProductStatisticResponse> getProductStatistic() {
        List<ProductStatisticResponse> response = customerStatisticService.getProductStatistic();
        return response;
    }

}
