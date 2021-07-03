/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisannio.CARE.model.util.Password;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author Acca
 *
 */

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long>{
	
	//@Query("FROM UserBean u  WHERE u.username =:username")         #non serve con il metodo di Luigi
	//UserBean findByUsername(@Param("username") String username);
	UserDAO findByUsername(String username);

	@Query("FROM UserDAO u WHERE u.userRole =:role AND u.activeUser > -2")
	Iterable<UserDAO> findUserByRole(@Param("role") String role);

	@Query("FROM UserDAO u WHERE u.email =:email AND u.activeUser > -2")
	UserDAO findByEmail(@Param("email") String email);

	@Query("FROM UserDAO u WHERE u.creationDate >:firstdate AND u.creationDate <:seconddate AND u.activeUser > -2")
	Iterable<UserDAO> findCreatedBetween(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	@Transactional
	@Query("UPDATE UserDAO u SET u.loginAttempts =:attempts where u.username =:username AND u.activeUser > -2")
	void updateUserLoginAttempts(@Param("attempts") int attempts, @Param("username") String username);

	@Query("FROM UserDAO u WHERE u.activeUser =:isactive")
	Iterable<UserDAO> filterUsersByState(@Param("isactive") short isactive);


	@Query("FROM UserDAO u WHERE u.lastAccess >:timestamp AND u.lastAccess <:currenttime  AND u.activeUser > -2")
	Iterable<UserDAO> filterUsersByLastLogin(@Param("timestamp") long timestamp, @Param("currenttime") long currenttime);




	// count queries

	@Query("SELECT COUNT(*) FROM UserDAO  AND u.activeUser > -2")
	long countAllUsers();

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.activeUser =:isactive  AND u.activeUser > -2")
	long countUsersByState(@Param("isactive") boolean isactive);

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.lastAccess >:timestamp AND u.lastAccess <:currenttime AND u.activeUser > -2 ")
	long countUsersByLastLogin(@Param("timestamp") long timestamp, @Param("currenttime") long currenttime);

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.userRole =:role AND u.activeUser > -2")
	long countUsersByRole(@Param("role") String role);

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.activeUser = -2")
	long countDeletedUsers();





	/*
	@Query("FROM users u  WHERE u.hiddenpass =:hiddenpass")	
	Iterable<UserBean> findByHiddenPassword(@Param("password") String password);
	
	@Query("FROM users u  WHERE u.loginAttempts =:loginAttempts")
	Iterable<UserBean> findByLoginAttempts(@Param("username") String loginAttempts);
	
	@Query("FROM users u  WHERE u.email =:email")
	Iterable<UserBean> findByEmail(@Param("email") String email);
	
	@Query("FROM users u  WHERE u.role =:role")
	Iterable<UserBean> findByRole(@Param("role") String role);
	
	@Query("FROM users u  WHERE u.creationDate =:creationDate")
	Iterable<UserBean> findByUserCreationDate(@Param("creationDate") String creationDate);
	
	@Query("FROM users u  WHERE u.lastAccess =:lastAccess")
	Iterable<UserBean> findByLastAccess(@Param("lastAccess") String lastAccess);
	
	@Query("FROM users u  WHERE u.username =:username")
	Iterable<UserBean> findByIsActiveUser(@Param("username") String username);
	*/
}
