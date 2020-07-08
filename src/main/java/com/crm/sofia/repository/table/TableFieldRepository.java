package com.crm.sofia.repository.table;

        import com.crm.sofia.model.table.TableField;
        import com.crm.sofia.repository.common.BaseRepository;
        import com.crm.sofia.repository.persistEntity.PersistEntityFieldRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface TableFieldRepository extends PersistEntityFieldRepository<TableField> {

    @Modifying
    @Query(" DELETE FROM TableField " +
            " WHERE id NOT IN (:ids) " +
            " AND table.id = :id ")
    void deleteObjectsNotInListForCustomComponent(@Param("ids") List<Long> ids, @Param("id") Long id);

}
