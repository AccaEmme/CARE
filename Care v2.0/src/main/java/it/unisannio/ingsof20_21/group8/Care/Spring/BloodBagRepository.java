package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BloodBagRepository extends JpaRepository<BloodBagBean, String> {
	/*
    @Query("from bloodbag b where b.serial =:serial")
    Iterable<BloodBagBean> findBySerial(@Param("serial") String serial);
    */
}
