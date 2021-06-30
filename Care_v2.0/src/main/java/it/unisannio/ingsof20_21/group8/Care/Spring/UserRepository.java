/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisannio.CARE.Model.Util.Password;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Acca
 *
 */

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long>{
	
	@Query("FROM UserBean u  WHERE u.username =:username")
	UserBean findByUsername(@Param("username") String username);

	@Query("FROM UserBean u WHERE u.userRole =:role")
	Iterable<UserBean> findUserByRole(@Param("role") String role);

	@Query("FROM UserBean u WHERE u.email =:email")
	UserBean findByEmail(@Param("email") String email);

	@Query("FROM UserBean u WHERE u.creationDate >:firstdate AND u.creationDate <:seconddate")
	Iterable<UserBean> findCreatedBetween(@Param("firstdate") long firstdate, @Param("seconddate") long seconddate);

	@Transactional
	@Query("UPDATE UserBean u SET u.loginAttempts =:attempts where u.username =:username")
	void updateUserLoginAttempts(@Param("attempts") int attempts, @Param("username") String username);

	@Query("SELECT COUNT(*) FROM UserBean ")
	long countAllUsers();

	@Query("SELECT COUNT(*) FROM UserBean u WHERE u.activeUser =:isactive ")
	long filterUsersByState(@Param("isactive") boolean isactive);

	@Query("SELECT COUNT(*) FROM UserBean u WHERE u.lastAccess >:timestamp ")
	long filterUsersByLastLogin(@Param("timestamp") long timestamp);

	@Query("SELECT COUNT(*) FROM UserBean u WHERE u.userRole =:role ")
	long filterUsersByRole(@Param("role") String role);



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
