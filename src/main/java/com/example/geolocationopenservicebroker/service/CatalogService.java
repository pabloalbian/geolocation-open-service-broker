package com.example.geolocationopenservicebroker.service;

import com.example.geolocationopenservicebroker.geolocation.MockServiceCatalog;
import com.example.geolocationopenservicebroker.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    @Autowired
    private MockServiceCatalog mockServiceCatalog;

    public Catalog getCatalog() {
        return mockServiceCatalog.mockCatalog();
    }
}
