package com.crm.sofia;

import com.crm.sofia.model.rita.Asset;
import com.crm.sofia.model.test.Order;
import com.crm.sofia.services.rita.AssetService;
import com.crm.sofia.services.rita.DroolsService;
import org.kie.api.runtime.KieSession;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class ShellCommands {

    private final AssetService assetService;
    private final DroolsService droolsService;
//    private final KieSession session;

    public ShellCommands(AssetService assetService,
                         DroolsService droolsService) {
        this.assetService = assetService;
        this.droolsService = droolsService;
    }

    @ShellMethod("Translate text from one language to another.")
    public String test(
//            @ShellOption String text
    ) {
        List<Asset> assets = this.assetService.generateAssetsList();
        return assets.toString();
    }

    @ShellMethod("Card options are HDFC, ICICI, DBS.")
    public String create(
            @ShellOption String key,
            @ShellOption String rdlFile) {
        return this.droolsService.createKiaSession(key, rdlFile).toString();
    }

    @ShellMethod("Get KeySessions Keys.")
    public String sessions() {
        return this.droolsService.getKeys().toString();
    }

    @ShellMethod("Run using Order for Model. Card options are HDFC, ICICI, DBS.")
    public String run_order(
            @ShellOption String key,
            @ShellOption int price,
                        @ShellOption String card) {
        Order order = new Order();
        order.setName("My Card");
        order.setCardType(card);
        order.setPrice(price);

        KieSession session = this.droolsService.getKiaSession(key);
        session.insert(order);
        session.fireAllRules();
        return order.toString();
    }

    @ShellMethod("Run with Assets model.")
    public String run(
            @ShellOption String key) {

        List<Asset> assets = assetService.generateAssetsList();

        KieSession session = this.droolsService.getKiaSession(key);
        session.insert(assets);
        session.fireAllRules();
        return "";
    }



//    @ShellMethod("Card options are HDFC, ICICI, DBS.")
//    public String order(@ShellOption int price,
//                        @ShellOption String card) {
//        Order order = new Order();
//        order.setName("My Card");
//        order.setCardType(card);
//        order.setPrice(price);
//
//        this.session.insert(order);
//        this.session.fireAllRules();
//        return order.toString();
//    }

}
