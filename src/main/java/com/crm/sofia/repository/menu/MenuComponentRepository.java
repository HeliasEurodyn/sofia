package com.crm.sofia.repository.menu;

import com.crm.sofia.model.component.CustomComponentField;
import com.crm.sofia.model.menu.MenuComponent;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuComponentRepository extends BaseRepository<MenuComponent> {

    List<MenuComponent> findAll();

}
