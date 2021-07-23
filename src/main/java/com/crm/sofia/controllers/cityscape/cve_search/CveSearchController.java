package com.crm.sofia.controllers.cityscape.cve_search;

import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductCpeDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductDTO;
import com.crm.sofia.services.cityscape.cve_search.CveSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cve-search")
public class CveSearchController {
    private final CveSearchService cveSearchService;

    public CveSearchController(CveSearchService cveSearchService) {
        this.cveSearchService = cveSearchService;
    }

    @GetMapping(path = "/vendors/{vendor}")
    public List<String> getVendorProducts(@PathVariable("vendor") String vendor) {
        VendorDTO vendorDTO = this.cveSearchService.getVendors(vendor);
        return vendorDTO.getVendor();
    }

    @GetMapping(path = "/vendors/{vendor}/products/{product}")
    public List<String> getVendorProducts(@PathVariable("vendor") String vendor,
                                              @PathVariable("product") String product) {
        VendorProductDTO vendorProductDTO = this.cveSearchService.getVendorProducts(vendor, product);
        return vendorProductDTO.getProduct();
    }

    @GetMapping(path = "/vendors/{vendor}/products/{product}/cpes")
    public List<String>  getCpes(@PathVariable("vendor") String vendor,
                                       @PathVariable("product") String product) {
        VendorProductCpeDTO vendorProductCpeDTO = this.cveSearchService.getVendorProductCpes(vendor, product);
        return vendorProductCpeDTO.getVersion();
    }

    @GetMapping(path = "/cves/by-cpe/{cpe}")
    public Object getCVes(@PathVariable("cpe") String cpe) {
        return this.cveSearchService.getCvesByCpe(cpe);
    }

}
