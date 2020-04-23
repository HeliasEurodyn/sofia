package com.crm.sofia.repository.component;

        import com.crm.sofia.model.component.CustomComponentField;
        import com.crm.sofia.repository.common.BaseRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface ComponentFieldRepository extends BaseRepository<CustomComponentField> {

    @Modifying
    @Query(" DELETE FROM CustomComponentField " +
            " WHERE id NOT IN (:ids) " +
            " AND customComponent.id = :id ")
    void deleteObjectsNotInListForCustomComponent(@Param("ids") List<Long> ids, @Param("id") Long id);

}
