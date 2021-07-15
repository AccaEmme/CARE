package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationRepository extends JpaRepository<UserDAO, Long> {
	/**
	 * updates the last user's access
	 * 
	 * @param username the user's username
	 * @param time     the current or new time
	 */
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.lastAccess = ?2 where u.username = ?1")
	void updateAccess(String username, long time);

	// users stuff

	/**
	 * gets the user from the provided username
	 * 
	 * @param username the user's username
	 * @return the user
	 */
	@Query("from UserDAO u WHERE u.username =?1")
	UserDAO getUserDaoFromUsername(String username);

	/**
	 * updates the user's login attempts
	 * 
	 * @param attempts the attempts to set
	 * @param username the user's username
	 */
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.loginAttempts = ?1 where u.username = ?2")
	void updateUserLoginAttempts(int attempts, String username);

	/**
	 * updates an user's state
	 * 
	 * @param state    the state to set
	 * @param username the user's username
	 */
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.activeUser = ?1 where u.username = ?2")
	void updateUserActiveUserByUsername(short state, String username);
}
