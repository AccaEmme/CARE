package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * used to specify all bloodbag controller queries
 */
@Repository
public interface BloodBagRepository extends JpaRepository<BloodBagDAO, String> {

	/**
	 * FINDS A LIST OF ELEMENTS WITH THE GIVEN BLOOD GROUP
	 * 
	 * @param group the group
	 * @return all the bags having that group
	 *
	 */
	@Query("from BloodBagDAO b where b.group =:group")
	Iterable<BloodBagDAO> findByGroup(@Param("group") String group);

	/**
	 * FINDS A LIST OF ELEMENTS WITH THE GIVEN SERIAL
	 * 
	 * @param serial the serial
	 * @return the bag having that serial
	 */
	@Query("from BloodBagDAO b where b.serial =:serial")
	BloodBagDAO findBySerial(@Param("serial") String serial);

	/**
	 * finds all the bags having a specific state
	 * 
	 * @param state the state
	 * @return all the bags having that state
	 */
	@Query("from BloodBagDAO b where b.state =:state")
	Iterable<BloodBagDAO> filterByState(@Param("state") String state);

	/**
	 * counts of all the bags
	 * 
	 * @return the count of all the bags
	 */
	// counts all the bloodbags
	@Query("SELECT COUNT(*) FROM BloodBagDAO")
	long countAll();

	/**
	 * counts all the bags having a specific group
	 * 
	 * @param group the blood group
	 * @return all the bags having that group
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.group =:group")
	long countByGroup(@Param("group") String group);

	/**
	 * counts all the bags having a specific state
	 * 
	 * @param state the state
	 * @return all the bags having that state
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.state =:state")
	long countByState(@Param("state") String state);

	/**
	 * gets the count of all bags that expire after the date
	 * 
	 * @param timestamp the date
	 * @return the count
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.expirationDate >:timestamp")
	long countExpirationAfterDate(@Param("timestamp") long timestamp);

	/**
	 * gets the count of all bags that were used after the date
	 * 
	 * @param timestamp the date
	 * @return the count
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.usedTimeStamp >:timestamp")
	long countUsedAfterDate(@Param("timestamp") long timestamp);

	/**
	 * gets the count of all bags that were used between two dates
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @return the count
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.usedTimeStamp >:firstdate AND b.usedTimeStamp <:seconddate")
	long countUsedBetweenDates(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	/**
	 * gets the count of all bags that were used between two dates
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @return the count
	 */
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.usedTimeStamp > seconddate")
	long countDroppedBetweenDates(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	/**
	 * gets all bags that expire before the date
	 * 
	 * @param timestamp the date
	 * @return all the bags expired before the date
	 */
	@Query("from BloodBagDAO b where b.expirationDate <:timestamp")
	Iterable<BloodBagDAO> findExpirationBeforeDate(@Param("timestamp") long timestamp);

	/**
	 * gets all bags that expire after the date
	 * 
	 * @param timestamp the date
	 * @return
	 */
	@Query("from BloodBagDAO b where b.expirationDate >:timestamp")
	Iterable<BloodBagDAO> findExpirationAfterDate(@Param("timestamp") long timestamp);

	/**
	 * gets all bags that expire between two dates
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @return all the bags expired between those dates
	 */
	@Query("from BloodBagDAO b where b.expirationDate >:firstdate AND b.expirationDate <:seconddate")
	Iterable<BloodBagDAO> findExpirationBetweenDate(@Param("firstdate") long firstdate,
			@Param("seconddate") long seconddate);

	/**
	 * gets all bags that expire between two dates for a given blood group
	 * 
	 * @param firstdate  the first date
	 * @param seconddate the second date
	 * @param bloodgroup the blood group
	 * @return the bags expired between those dates having that blood group
	 */
	@Query("from BloodBagDAO b where b.expirationDate >:firstdate AND b.expirationDate <:seconddate AND b.group =:bloodgroup")
	Iterable<BloodBagDAO> findExpirationBetweenDate_bloodGroup(@Param("firstdate") long firstdate,
			@Param("seconddate") long seconddate, @Param("bloodgroup") String bloodgroup);

	// metodi di update

	/**
	 * updates a bag's state by serial
	 * 
	 * @param state  the new state
	 * @param serial the bag's serial
	 */
	@Modifying
	@Transactional
	@Query("UPDATE BloodBagDAO b SET b.state = ?1 where b.serial = ?2")
	void updateBloodBagStateBySerial(String state, String serial);

	/**
	 * updates a bag's used timestamp by serial
	 * 
	 * @param timestamp the new timestamp
	 * @param serial    the bag's serial
	 */
	@Modifying
	@Transactional
	@Query("UPDATE BloodBagDAO b SET b.usedTimeStamp = ?1 where b.serial = ?2")
	void updateBloodBagUsedTimestampBySerial(long timestamp, String serial);

}
