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

	//users stuff
	@Query("from UserDAO u WHERE u.username =?1")
	UserDAO getUserDaoFromUsername(String username);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.loginAttempts = ?1 where u.username = ?2")
	void updateUserLoginAttempts(int attempts,String username);

	// modifica lo stato di un user cercandolo per username
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.activeUser = ?1 where u.username = ?2")
	void updateUserActiveUserByUsername(short state, String username);
}
