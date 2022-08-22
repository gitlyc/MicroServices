package com.lyc.serviceprovider01.service;


import com.lyc.serviceprovider01.entity.Product;

import java.util.List;

public interface  ProductService {
    /**
     * 查询商品列表
     *
     * @return
     */
    List<Product> selectProductList();

}