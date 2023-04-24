package com.order.Service.Impl;

import com.order.Service.DTO.ProductDTO;
import com.order.Service.DTO.VendorDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GetVendorService {
    //Address for vendor
    private static final String API_BASE_URL = "http://localhost:8083/api/v1/vendor/order";
    private RestTemplate restTemplate = new RestTemplate();
    //Change with vendor DTO
    public VendorDTO getById(Long id) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<VendorDTO> entity = new HttpEntity<>(headers);
            ResponseEntity<VendorDTO> response = restTemplate.exchange(API_BASE_URL + "/" + id, HttpMethod.GET, entity, VendorDTO.class);
            return response.getBody();
        } catch (Exception e) {
            throw new Exception("Error in getting vendor by id");
        }
    }
}






