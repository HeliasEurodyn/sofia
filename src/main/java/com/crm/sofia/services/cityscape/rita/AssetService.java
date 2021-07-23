package com.crm.sofia.services.cityscape.rita;

import com.crm.sofia.model.cityscape.cve_search.rita.Asset;
import com.crm.sofia.model.cityscape.cve_search.rita.Interface;
import com.crm.sofia.model.cityscape.cve_search.rita.Vulnerability;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {


    public List<Asset> generateAssetsList() {
        List<Asset> assets = new ArrayList<>();

        /* New Asset */
        Asset asset = new Asset();
        asset.setCode("a1");
        asset.setName("My Computer");
        assets.add(asset);

        /* New Interfaces */
        List<Interface> interfaces = new ArrayList<>();
        Interface iface = new Interface();
        iface.setCode("i1");
        iface.setName("Keyboard");

        assets.add(asset);
        Interface iface2 = new Interface();
        iface2.setCode("i2");
        iface2.setName("usb port");
        interfaces.add(iface2);

        asset.setInterfaces(interfaces);

        /* New Vulnerabilities */
        List<Vulnerability> vulnerabilities = new ArrayList<>();
        Vulnerability vulnerability = new Vulnerability();
        vulnerability.setCode("v1");
        vulnerability.setName("Escape Key Down");
        vulnerability.setImpactLevel(10.2);
        vulnerability.setLevel(22.5);
        vulnerabilities.add(vulnerability);

        Vulnerability vulnerability2 = new Vulnerability();
        vulnerability2.setCode("v2");
        vulnerability2.setName("Enter Key Down");
        vulnerability2.setImpactLevel(1.2);
        vulnerability2.setLevel(4.5);
        vulnerabilities.add(vulnerability2);

        iface.setVulnerabilities(vulnerabilities);

        List<Vulnerability> vulnerabilities2 = new ArrayList<>();
        vulnerability = new Vulnerability();
        vulnerability.setCode("v1");
        vulnerability.setName("Usb with Virus");
        vulnerability.setImpactLevel(4.2);
        vulnerability.setLevel(1.5);
        vulnerabilities2.add(vulnerability);

        vulnerability2 = new Vulnerability();
        vulnerability2.setCode("v2");
        vulnerability2.setName("Usb with OS to format");
        vulnerability2.setImpactLevel(4.5);
        vulnerability2.setLevel(7.5);
        vulnerabilities2.add(vulnerability2);

        iface2.setVulnerabilities(vulnerabilities2);

        return assets;
    }

}
