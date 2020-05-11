package com.crm.sofia.repository.menu;

import com.crm.sofia.model.component.CustomComponentField;
import com.crm.sofia.model.menu.MenuItemComponent;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemComponentRepository extends BaseRepository<MenuItemComponent> {

    @Modifying
    @Query(" DELETE FROM MenuItemComponent " +
            " WHERE id NOT IN (:ids) " +
            " AND menuComponent.id = :id ")
    void deleteObjectsNotInListForParentId(@Param("ids") List<Long> ids, @Param("id") Long id);

}
