package com.crm.sofia.repository.notification;

import com.crm.sofia.dto.notification.NotificationDTO;
import com.crm.sofia.model.notification.Notification;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends BaseRepository<Notification> {

    @Query(" SELECT new com.crm.sofia.dto.notification.NotificationDTO(n.id,n.title)  " +
            " FROM Notification n " +
            " WHERE n.receiverId =:id " +
            " ORDER BY n.createdOn DESC")
    List<NotificationDTO> findAllByReceiverId(@Param("id") String id);

    @Query(" SELECT new com.crm.sofia.dto.notification.NotificationDTO(n.id,n.title)  " +
            " FROM Notification n " +
            " WHERE n.receiverId =:id " +
            " And n.status = 'unread' " +
            " ORDER BY n.createdOn DESC")
    List<NotificationDTO> findUnreadByReceiverId(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Notification SET status = 'read'  WHERE id = :id")
    void makeRead(@Param("id") String id);

}
