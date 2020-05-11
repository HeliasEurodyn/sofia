package com.crm.sofia.repository.menu;

import com.crm.sofia.model.component.CustomComponentField;
import com.crm.sofia.model.menu.MenuComponent;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuComponentRepository extends BaseRepository<MenuComponent> {

    List<MenuComponent> findAll();

    @Query(" SELECT DISTINCT c " +
            " FROM MenuComponent c " +
            " JOIN FETCH c.menuFieldList  " +
            " WHERE c.id =:id ")
    Optional<MenuComponent> findTreeById(Long id);
}
