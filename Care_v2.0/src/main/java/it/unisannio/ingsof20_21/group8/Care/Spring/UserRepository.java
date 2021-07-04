/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisannio.CARE.model.util.Password;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;



@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long>{
	
	//@Query("FROM UserBean u  WHERE u.username =:username")         #non serve con il metodo di Luigi
	//UserBean findByUsername(@Param("username") String username);
	UserDAO findByUsername(String username);

	@Query("FROM UserDAO u WHERE u.userRole =:role AND u.activeUser > -2")
	Iterable<UserDAO> findUserByRole(@Param("role") String role);

	@Query("FROM UserDAO u WHERE u.email =:email AND u.activeUser > -2")
	UserDAO findByEmail(@Param("email") String email);

	@Query("FROM UserDAO u WHERE u.creationDate >:firstdate AND u.creationDate <:seconddate")
	Iterable<UserDAO> findCreatedBetween(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.loginAttempts = ?1 where u.username = ?2")
	void updateUserLoginAttempts(int attempts,String username);
	//void updateUserLoginAttempts(@Param("attempts") int attempts, @Param("username") String username);

	// modifica lo stato di un user cercandolo per username
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.activeUser = ?1 where u.username = ?2")
	void updateUserActiveUserByUsername(short state, String username);

	// modifica lo stato di un user cercandolo per email
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.activeUser = ?1 where u.email = ?2")
	void updateUserActiveUserByEmail(short state, String email);

	//aggiorno la password
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.password = ?1 where u.username = ?2")
	void updateUserPasswordByUsername(String password, String username);

	//aggiorno la password temporanea
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.temppass = ?1 where u.username = ?2")
	void updateUserTempPasswordByUsername(String temppass, String username);


	// query di aggiornamento user
	//update username by id
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.username = ?1 where u.idUser = ?2")
	void updateUserUsernameByID(String username, long id);


	//update temppass -> password by id
	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.temppass = ?1 where u.idUser = ?2")
	void updateUserTemppassByID(String temppass, long id);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.password = ?1 where u.idUser = ?2")
	void updateUserPasswordByID(String password, long id);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.email = ?1 where u.idUser = ?2")
	void updateUserEmailByID(String email, long id);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.userRole = ?1 where u.idUser = ?2")
	void updateUserRoleByID(String role, long id);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.loginAttempts = ?1 where u.idUser = ?2")
	void updateUserLoginAttemptsByID(int loginAttempts, long id);

	@Modifying
	@Transactional
	@Query("UPDATE UserDAO u SET u.activeUser = ?1 where u.idUser = ?2")
	void updateUserActiveUserByID(short activeuser, long id);


	@Query("FROM UserDAO u WHERE u.activeUser =:isactive")
	Iterable<UserDAO> filterUsersByState(@Param("isactive") short isactive);


	@Query("FROM UserDAO u WHERE u.lastAccess >:timestamp AND u.lastAccess <:currenttime")
	Iterable<UserDAO> filterUsersByLastLogin(@Param("timestamp") long timestamp, @Param("currenttime") long currenttime);




	// count queries
	@Query("SELECT COUNT(*) FROM UserDAO")
	long countAllUsers();

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.activeUser =:isactive ")
	long countUsersByState(@Param("isactive") short isactive);

	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.lastAccess >:timestamp AND u.lastAccess <:currenttime")
	long countUsersByLastLogin(@Param("timestamp") long timestamp, @Param("currenttime") long currenttime);


	@Query("SELECT COUNT(*) FROM UserDAO u WHERE u.userRole =:role ")
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
