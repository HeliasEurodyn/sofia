package com.crm.sofia.repository.menu;

import com.crm.sofia.model.menu.MenuField;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuFieldRepository extends BaseRepository<MenuField> {

//    @Modifying
//    @Query(" DELETE FROM MenuField " +
//            " WHERE id NOT IN (:ids) " +
//            " AND menu.id = :id ")
//    void deleteObjectsNotInListForParentId(@Param("ids") List<Long> ids, @Param("id") Long id);

}
