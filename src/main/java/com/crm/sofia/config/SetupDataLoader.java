package com.crm.sofia.config;

import com.crm.sofia.dto.sofia.user.SocialProvider;
import com.crm.sofia.model.sofia.menu.Menu;
import com.crm.sofia.model.sofia.menu.MenuField;
import com.crm.sofia.model.sofia.user.Role;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.repository.sofia.menu.MenuRepository;
import com.crm.sofia.repository.sofia.user.RoleRepository;
import com.crm.sofia.repository.sofia.user.UserRepository;
import com.crm.sofia.services.data_transfer.DataTransferService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataTransferService dataTransferService;

//  @Autowired
//  private CveSearchSettingsService cveSearchSettingsService;

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        Role userRole = this.createRoleIfNotFound(Role.ROLE_USER);
        Role adminRole = this.createRoleIfNotFound(Role.ROLE_ADMIN);
        Role modRole = this.createRoleIfNotFound(Role.ROLE_MODERATOR);
        Menu sidebarMenu = this.createSidebarMenu();
        Menu headerMenu = this.createHeaderMenu();
        this.createUserIfNotFound(Set.of(userRole, adminRole, modRole), sidebarMenu, headerMenu);
        this.sofiaImports();
//        this.cveSearchSettingsService.tryInsertVendorsIfEmptyTable();
    }

    private final void sofiaImports() throws Exception {
        File file = new File("./sofia_imports");
        if (file.exists() && file.isDirectory()) {
            List<Path> paths = Files.list(Paths.get(file.getPath())).collect(Collectors.toList());

            for (Path path : paths) {
                String extension = path.toFile().getAbsolutePath().substring(path.toFile().getAbsolutePath().lastIndexOf("."));
                if ((extension == null ? "" : extension).equals(".sofia")) {
                    byte[] bytes = Files.readAllBytes(path);
                    this.dataTransferService.importByteArray(bytes);
                }
            }
        }
    }

    private final User createUserIfNotFound(Set<Role> roles, Menu sidebarMenu, Menu headerMenu) {
        User user = userRepository.findByEmail("admin@sofia.com");
        if (user == null) {
            user = new User();
            user.setUsername("admin");
            user.setStatus(AppConstants.Types.UserStatus.enabled);
            user.setEmail("admin@sofia.com");
            user.setPassword(passwordEncoder.encode("@dmin@"));
            user.setRolesSet(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            user.setCreatedOn(Instant.now());
            user.setModifiedOn(Instant.now());
            user.setSidebarMenu(sidebarMenu);
            user.setHeaderMenu(headerMenu);
            user.setLoginNavCommand("STATICPAGE[NAME:author-dashboard]");
            user = userRepository.save(user);
        }
        return user;
    }

    private final Menu createHeaderMenu() {
        Menu savedMenu = menuRepository.findFirstByName("Author Header Menu");
        if (savedMenu == null) {

            Menu menu = new Menu();
            menu.setAccessControlEnabled(false);
            menu.setName("Author Header Menu");
            menu.setTranslations(new ArrayList<>());
            menu.setMenuFieldList(new ArrayList<>());

            MenuField menuField = new MenuField();
            menuField.setName("Profile");
            menuField.setIcon("fa-user");
            menuField.setCommand("STATICPAGE[NAME:user,TITLE:Profile]");
            menuField.setShortOrder(1L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Logout");
            menuField.setIcon("fa-power-off");
            menuField.setCommand("#logout#");
            menuField.setShortOrder(2L);
            menu.getMenuFieldList().add(menuField);

            savedMenu = menuRepository.save(menu);
        }
        return savedMenu;
    }

    private final Menu createSidebarMenu() {
        Menu savedMenu = menuRepository.findFirstByName("Author Menu");
        if (savedMenu == null) {

            Menu menu = new Menu();
            menu.setAccessControlEnabled(false);
            menu.setName("Author Menu");
            menu.setTranslations(new ArrayList<>());
            menu.setMenuFieldList(new ArrayList<>());

            MenuField menuField = new MenuField();
            menuField.setName("Users");
            menuField.setIcon("fa-users");
            menuField.setCommand("STATICPAGE[NAME:user-list,TITLE:Users]");
            menuField.setShortOrder(1L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Roles");
            menuField.setIcon("fa-users");
            menuField.setCommand("STATICPAGE[NAME:role-list,TITLE:User Groups]");
            menuField.setShortOrder(2L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Menu Designer");
            menuField.setIcon("fa-bars");
            menuField.setCommand("STATICPAGE[NAME:menu-designer-list,TITLE:Menu Designer]");
            menuField.setShortOrder(3L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("List Designer");
            menuField.setIcon("fa-list");
            menuField.setCommand("STATICPAGE[NAME:list-designer-list,TITLE:List Designer]");
            menuField.setShortOrder(4L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Form Designer");
            menuField.setIcon("fa-object-group");
            menuField.setCommand("STATICPAGE[NAME:form-designer-list,TITLE:Form Designer]");
            menuField.setShortOrder(5L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Component Designer");
            menuField.setIcon("fa-code-branch");
            menuField.setCommand("STATICPAGE[NAME:component-designer-list,TITLE:Compoent Designer]");
            menuField.setShortOrder(6L);
            menu.getMenuFieldList().add(menuField);

            MenuField folderMenuField = new MenuField();
            folderMenuField.setName("Persist Entities");
            folderMenuField.setIcon("fa-folder");
            folderMenuField.setCommand("#parent-menu#");
            folderMenuField.setShortOrder(7L);
            folderMenuField.setMenuFieldList(new ArrayList<>());
            menu.getMenuFieldList().add(folderMenuField);

            menuField = new MenuField();
            menuField.setName("Table Designer");
            menuField.setIcon("fa-table");
            menuField.setCommand("STATICPAGE[NAME:table-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(8L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("View Designer");
            menuField.setIcon("fa-table");
            menuField.setCommand("STATICPAGE[NAME:view-designer-list,TITLE:View Designer]");
            menuField.setShortOrder(9L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("App View Designer");
            menuField.setIcon("fa-table");
            menuField.setCommand("STATICPAGE[NAME:appview-designer-list,TITLE:App View Designer]");
            menuField.setShortOrder(10L);
            folderMenuField.getMenuFieldList().add(menuField);

            folderMenuField = new MenuField();
            folderMenuField.setName("Dashboard");
            folderMenuField.setIcon("fa-folder");
            folderMenuField.setCommand("#parent-menu#");
            folderMenuField.setShortOrder(11L);
            folderMenuField.setMenuFieldList(new ArrayList<>());
            menu.getMenuFieldList().add(folderMenuField);

            menuField = new MenuField();
            menuField.setName("Chart Designer");
            menuField.setIcon("fa-cogs");
            menuField.setCommand("STATICPAGE[NAME:chart-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(12L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Info Card Designer");
            menuField.setIcon("fa-cogs");
            menuField.setCommand("STATICPAGE[NAME:info-card-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(13L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Html part Deshgner");
            menuField.setIcon("fa-cogs");
            menuField.setCommand("STATICPAGE[NAME:html-dashboard-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(14L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Dashboard Designer");
            menuField.setIcon("fa-cogs");
            menuField.setCommand("STATICPAGE[NAME:dashboard-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(15L);
            folderMenuField.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Report Designer");
            menuField.setIcon("fa-file-invoice");
            menuField.setCommand("STATICPAGE[NAME:report-designer-list,TITLE:Table Designer]");
            menuField.setShortOrder(17L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Xls Import Designer");
            menuField.setIcon("fa-file-excel");
            menuField.setCommand("STATICPAGE[NAME:xls-import-designer-list]");
            menuField.setShortOrder(18L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Search Designer");
            menuField.setIcon("fa-search");
            menuField.setCommand("STATICPAGE[NAME:search-designer-list]");
            menuField.setShortOrder(19L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Custom Queries");
            menuField.setIcon("fa-code");
            menuField.setCommand("STATICPAGE[NAME:custom-query-list,TITLE:Table Designer]");
            menuField.setShortOrder(20L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Expression Viewer");
            menuField.setIcon("fa-calculator");
            menuField.setCommand("STATICPAGE[NAME:expression-viewer]");
            menuField.setShortOrder(21L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Languages");
            menuField.setIcon("fa-globe");
            menuField.setCommand("STATICPAGE[NAME:language-designer-list,TITLE:User Groups]");
            menuField.setShortOrder(22L);
            menu.getMenuFieldList().add(menuField);

            menuField = new MenuField();
            menuField.setName("Data Transfer");
            menuField.setIcon("fa-cloud");
            menuField.setCommand("STATICPAGE[NAME:data-transfer-list]");
            menuField.setShortOrder(23L);
            menu.getMenuFieldList().add(menuField);

            savedMenu = menuRepository.save(menu);
        }
        return savedMenu;
    }

    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findFirstByName(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }
}
