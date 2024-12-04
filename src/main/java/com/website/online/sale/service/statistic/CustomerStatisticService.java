package com.website.online.sale.service.statistic;

import com.website.online.sale.dtos.response.statistic.CustomerStatisticResponse;
import com.website.online.sale.dtos.response.statistic.ProductStatisticResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerStatisticService {
    List<CustomerStatisticResponse> getCustomerStatistic();

    List<ProductStatisticResponse> getProductStatistic();
}
