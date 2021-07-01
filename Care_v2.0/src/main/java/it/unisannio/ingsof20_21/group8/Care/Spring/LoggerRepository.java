package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerBean, Long> {

    /**
     * @param usermail the given user email
     * @return all the logs of that specific user
     */
    @Query("FROM LoggerBean l WHERE l.currentUserEmail =:usermail")
    Iterable<LoggerBean> filterLogsByEmail(@Param("usermail") String usermail);

    /**
     * @param username the given user username
     * @return all the logs of that specific user
     */
    @Query("FROM LoggerBean l WHERE l.currentUserUsername =:username")
    Iterable<LoggerBean> filterLogsByUsername(@Param("username") String username);


    /**
     * @param id the id to find
     * @return the log corrisponding to the id
     */
    @Query("FROM LoggerBean l WHERE l.idLog =:id")
    LoggerBean findLogById(@Param("id") long id);


    @Query("FROM LoggerBean l WHERE l.currentTimeStamp >:first AND l.currentTimeStamp <:second")
    Iterable<LoggerBean> filterLogsByDate(@Param("first") long first, @Param("second") long second);
}
