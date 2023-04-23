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
    private static final String API_BASE_URL = "http://localhost:8083/api/v1/vendor";
    private RestTemplate restTemplate = new RestTemplate();
    //Change with vendor DTO
    public VendorDTO getById(Long id) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String apiUrl = String.format("%s/%s", API_BASE_URL, id);
        //Change with vendor DTO
        ResponseEntity<VendorDTO> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                VendorDTO.class);

        return response.getBody();
    }
}






