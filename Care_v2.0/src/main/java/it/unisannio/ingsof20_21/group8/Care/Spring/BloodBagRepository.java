package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BloodBagRepository extends JpaRepository<BloodBagDAO, String> {
	
	/**FINDS A LIST OF ELEMENTS WITH THE GIVEN BLOOD GROUP*/
	@Query("from BloodBagDAO b where b.group =:group")
	Iterable<BloodBagDAO> findByGroup(@Param("group") String group);
	
	/**FINDS A LIST OF ELEMENTS WITH THE GIVEN SERIAL*/
	@Query("from BloodBagDAO b where b.serial =:serial")
	Iterable<BloodBagDAO> findBySerial(@Param("serial") String serial);

	@Query("from BloodBagDAO b where b.state =:state")
	Iterable<BloodBagDAO> filterByState(@Param("state") String state);

	// counts all the bloodbags
	@Query("SELECT COUNT(*) FROM BloodBagDAO")
	long countAll();

	// counts by group
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.group =:group")
	long countByGroup(@Param("group") String group);

	// counts by state
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.state =:state")
	long countByState(@Param("state") String state);

	//gets the count of all bags that expire after the date...
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.expirationDate >:timestamp")
	long countExpirationAfterDate(@Param("timestamp") long timestamp);

	//gets the count of all bags that were used after the date...
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.usedTimeStamp >:timestamp")
	long countUsedAfterDate(@Param("timestamp") long timestamp);


	//gets the count of all bags that were used between two dates...
	@Query("SELECT COUNT(*) FROM BloodBagDAO b where b.usedTimeStamp >:firstdate AND b.usedTimeStamp <:seconddate")
	long countUsedBetweenDates(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	//gets all bags that expire before the date...
	@Query("from BloodBagDAO b where b.expirationDate <:timestamp")
	Iterable<BloodBagDAO> findExpirationBeforeDate(@Param("timestamp") long timestamp);

	//gets all bags that expire after the date...
	@Query("from BloodBagDAO b where b.expirationDate >:timestamp")
	Iterable<BloodBagDAO> findExpirationAfterDate(@Param("timestamp") long timestamp);



	//gets all bags that expire between two dates...
	@Query("from BloodBagDAO b where b.expirationDate >:firstdate AND b.expirationDate <:seconddate")
	Iterable<BloodBagDAO> findExpirationBetweenDate(@Param("firstdate") long firstdate,
													 @Param("seconddate") long seconddate);

	//gets all bags that expire between two dates for a given blood group...
	@Query("from BloodBagDAO b where b.expirationDate >:firstdate AND b.expirationDate <:seconddate AND b.group =:bloodgroup")
	Iterable<BloodBagDAO> findExpirationBetweenDate_bloodGroup(@Param("firstdate") long firstdate,
																@Param("seconddate") long seconddate,
																@Param("bloodgroup") String bloodgroup);



}
