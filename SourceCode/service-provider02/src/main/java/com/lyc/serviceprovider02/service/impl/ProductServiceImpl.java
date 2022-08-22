package com.lyc.serviceprovider02.service.impl;


import com.lyc.serviceprovider02.entity.Product;
import com.lyc.serviceprovider02.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 商品服务
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1, "苹果手机", 2, 9000D),
                new Product(2, "华为手机", 1, 6000D),
                new Product(3, "小米手机", 5, 4000D)
        );
    }
}
