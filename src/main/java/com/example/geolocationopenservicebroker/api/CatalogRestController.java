package com.example.geolocationopenservicebroker.api;


import com.example.geolocationopenservicebroker.api.dto.CatalogResponseDto;
import com.example.geolocationopenservicebroker.model.Catalog;
import com.example.geolocationopenservicebroker.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogRestController {

    private final CatalogService catalogService;

    CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/v2/catalog")
    public ResponseEntity<CatalogResponseDto> getCatalog() {
        Catalog catalog = catalogService.getCatalog();

        CatalogResponseDto response = new CatalogResponseDto(catalog.getServiceDefinitions());

        return ResponseEntity.ok().body(response);
    }
}
