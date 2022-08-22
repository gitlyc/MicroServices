package com.lyc.serviceconsumer.service.impl;


import com.lyc.serviceconsumer.entity.Order;
import com.lyc.serviceconsumer.entity.Product;
import com.lyc.serviceconsumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient; // Ribbon 负载均衡器


    @Override
    public Order selectOrderById(Integer id) {
        return new Order(id, "order-001", "中国", 31994D,
                selectProductListByLoadBalancerClient());
    }

    private List<Product> selectProductListByLoadBalancerClient() {
        StringBuffer sb = null;

        // 根据服务名称获取服务
        ServiceInstance si = loadBalancerClient.choose("service-provider");
        if (null == si){
            return null;
        }

        sb = new StringBuffer();
        sb.append("http://" + si.getHost() + ":" + si.getPort() + "/product/list");
        System.out.println(">>>>>" + si.getPort());

        // ResponseEntity: 封装了返回数据
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                sb.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }
}
