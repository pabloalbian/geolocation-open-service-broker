package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.geolocation.MockServiceCatalog;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class CatalogService {

    @Autowired
    private MockServiceCatalog mockServiceCatalog;
}
