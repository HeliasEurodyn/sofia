package com.crm.sofia.services.cityscape.cve_search;

import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductCpeDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductDTO;
import com.crm.sofia.rest_call.cityscape.CveSearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class CveSearchService {

    private final CveSearchRestTemplate cveSearchRestTemplate;

    public CveSearchService(CveSearchRestTemplate cveSearchRestTemplate) {
        this.cveSearchRestTemplate = cveSearchRestTemplate;
    }

    public VendorDTO getVendors(String vendor) {
        return this.cveSearchRestTemplate.getVendors(vendor);
    }

    public VendorProductDTO getVendorProducts(String vendor, String product) {
        return this.cveSearchRestTemplate.getVendorProducts(vendor, product);
    }

    public VendorProductCpeDTO getVendorProductCpes(String vendor, String product) {
        return this.cveSearchRestTemplate.getVendorProductCpes(vendor, product);
    }


    public Object getCvesByCpe(String cpe) {
        return this.cveSearchRestTemplate.getCvesByCpe(cpe);
    }
}
