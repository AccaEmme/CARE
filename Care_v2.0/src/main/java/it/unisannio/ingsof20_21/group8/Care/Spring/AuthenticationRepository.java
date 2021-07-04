package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationRepository extends JpaRepository<UserDAO, Long>{
	@Modifying
	@Transactional
	 @Query("UPDATE UserDAO u SET u.lastAccess = ?2 where u.username = ?1")
	void updateAccess(String username, long time);
}
