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
	
}
