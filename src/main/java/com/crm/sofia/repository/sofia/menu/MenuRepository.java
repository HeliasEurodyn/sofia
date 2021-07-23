package com.crm.sofia.repository.sofia.menu;

import com.crm.sofia.model.sofia.menu.Menu;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> findAll();

    @Query(" SELECT DISTINCT c " +
            " FROM Menu c " +
            " LEFT JOIN FETCH c.menuFieldList fl " +
            " WHERE c.id =:id " +
            " ORDER BY fl.shortOrder")
    Optional<Menu> findTreeById(@Param("id") Long id);
}
