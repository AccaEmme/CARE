package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BloodBagRepository extends JpaRepository<BloodBagBean, String> {
	
	/**FINDS A LIST OF ELEMENTS WITH THE GIVEN BLOOD GROUP*/
	@Query("from BloodBagBean b where b.group =:group")
	Iterable<BloodBagBean> findByGroup(@Param("group") String group);
	
	/**FINDS A LIST OF ELEMENTS WITH THE GIVEN SERIAL*/
	@Query("from BloodBagBean b where b.serial =:serial")
	Iterable<BloodBagBean> findBySerial(@Param("serial") String serial);

	@Query("from BloodBagBean b where b.state =:state")
	Iterable<BloodBagBean> filterByState(@Param("state") String state);

	// counts all the bloodbags
	@Query("SELECT COUNT(*) FROM BloodBagBean")
	long countAll();

	// counts by group
	@Query("SELECT COUNT(*) FROM BloodBagBean b where b.group =:group")
	long countByGroup(@Param("group") String group);

	// counts by state
	@Query("SELECT COUNT(*) FROM BloodBagBean b where b.state =:state")
	long countByState(@Param("state") String state);

}
