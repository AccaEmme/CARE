package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends JpaRepository<RequestDAO, Long> {

    //change the request's state
    @Modifying
    @Transactional
    @Query("UPDATE RequestDAO r SET r.state = ?1 where r.serial = ?2")
    void updateRequestStateBySerial(String state, String serial);

    @Query("FROM RequestDAO r where r.serial = ?1")
    RequestDAO getRequestFromSerial(String serial);
}
