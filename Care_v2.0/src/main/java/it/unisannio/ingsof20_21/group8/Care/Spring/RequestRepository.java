package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * used to specify the p2p requests queries
 */
@Repository
public interface RequestRepository extends JpaRepository<RequestDAO, Long> {

    /**
     * used to update a request state
     * @param state the request state: pending, accepted, refused...
     * @param serial the request/bag state
     */
    //change the request's state
    @Modifying
    @Transactional
    @Query("UPDATE RequestDAO r SET r.state = ?1 where r.serial = ?2")
    void updateRequestStateBySerial(String state, String serial);

    /**
     * used to get a request by it's serial
     * @param serial the request/bag state: pending, accepted, refused...
     * @return the request
     */
    @Query("FROM RequestDAO r where r.serial = ?1")
    RequestDAO getRequestFromSerial(String serial);

    /**
     * used to get all the requests having a specific state
     * @param state the request state: pending, accepted, refused...
     * @return all the request having that state
     */
    @Query("FROM RequestDAO r where r.state = ?1")
    Iterable<RequestDAO> getRequestsByState(String state);

    /**
     * get all requests from a specific node having a specific state
     * @param state the request state
     * @param node the node id
     * @return all the request having a specific state and from a specific node
     */
    @Query("FROM RequestDAO r where r.state = ?1 AND r.requestingNode = ?2")
    Iterable<RequestDAO> getRequestByStateAndNode(String state, String node);
}
