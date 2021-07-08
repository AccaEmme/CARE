/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.report.UserReport;
import it.unisannio.CARE.model.user.Role;
import it.unisannio.CARE.model.user.User;
import it.unisannio.CARE.model.user.UsersStates;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.Password;


@CrossOrigin("*")
@RestController
@Consumes("application/json")
@Produces("application/json")
//@Path("rest")
public  class UserController /*implements ContainerResponseFilter */{
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
    	this.userRepo = userRepo;
    }
/*
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }*/


	/**
	 * this method gets all the users in the database (includes deleted users)
	 * @return an iterable of Users
	 * request: 127.0.0.1:8087/user/get/all
	 */
	@GetMapping("/user/get/all")
	public Iterable<UserDAO> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	 * this method is used to get the user having the provided status
	 * @param status used to search the users
	 * @return the users having the provided status
	 * request: 127.0.0.1:8087/user/get/email/dtarver6@simplemachines.org
	 */
	@GetMapping("/user/get/bystatus/{status}")
	public Iterable<UserDAO> getByStatusUsers(@PathVariable short status) {
		return userRepo.findByUserState(status);
	}

	
	/**
	 * This method is used to get all not deleted users
	 * @return iterable list of not deleted users
	 * request: 127.0.0.1:8087/user/get/notdeleted
	 */
	@GetMapping("/user/get/notdeleted")
	public Iterable<UserDAO> getNotDeletedUsers() {
		return userRepo.findNotDeletedUsers();
	}
	
	/**
	 * This method is used to get all deleted users
	 * @return iterable list of deleted users
	 * request: 127.0.0.1:8087/user/get/deleted
	 */
	@GetMapping("/user/get/deleted")
	public Iterable<UserDAO> getDeletedUsers() {
		return userRepo.findByUserState(UsersStates.DELETED);
	}

	/**
	 * this method is used to get the user having the provided email
	 * @param email used to search the user
	 * @return the user having the provided email
	 * request: 127.0.0.1:8087/user/get/email/dtarver6@simplemachines.org
	 */
	@GetMapping("/user/get/email/{email}")
	public UserDAO getUserByEmail(@PathVariable String email){
		return userRepo.findByEmail(email);
	}

    /*
      login():attempts
     */


	/**
	 * @param username the provided username to search the user
	 * @return the user having the provided username
	 * request: 127.0.0.1:8087/user/get/username/dedland7
	 */
	@GetMapping("/user/get/username/{username}")
	public UserDAO getUserByUsername(@PathVariable String username){
		return userRepo.findByUsername(username);
	}


	/**
	 * @param role the provided role the user must have
	 * @return all the users having the provided role
	 * request: 127.0.0.1:8087/user/get/role/Administrator
	 */
	@GetMapping("/user/get/role/{role}")
	public Iterable<UserDAO> getUserByRole(@PathVariable String role){
		return userRepo.findUserByRole(role);
	}


	/**
	 * @param firstdate the starting date
	 * @param seconddate the ending date
	 * @return all the users created between the first and second date
	 * request:  127.0.0.1:8087/user/get/created/1624217670000/1624995270000
	 */
	@GetMapping("user/get/created/{firstdate}/{seconddate}")
	public Iterable<UserDAO> getUserCreatedBetween(@PathVariable long firstdate, @PathVariable long seconddate){
		return userRepo.findCreatedBetween(firstdate,seconddate);
	}

	/**
	 * @return all the active users
	 */
	@GetMapping("user/get/active")
	public Iterable<UserDAO> getActiveUsers(){
		return userRepo.filterUsersByState(UsersStates.ACTIVE);
	}

	/**
	 * @return all the inactive users
	 */
	@GetMapping("user/get/inactive")
	public Iterable<UserDAO> getInactiveUsers(){
		return userRepo.filterUsersByState(UsersStates.INACTIVE);
	}


	/**
	 * @return the report
	 * example:
	 * HTTP request: 127.0.0.1:8087/user/report
	 * 	  Output:
	 *          {
	 * 	      "total": 1000,
	 * 	      "activeUsers": 482,
	 * 	      "loggedLast24Hours": 2,
	 * 	      "administrators": 482,
	 * 	      "storeManagers": 322,
	 * 	      "officers": 340,
	 * 	      "deactivatedUsers": 518
	 *      }
	 */
	@GetMapping("user/report")
	public UserReport getUserReport(){
		long total = userRepo.countAllUsers();
		System.out.println(total);
		long activeUsers = userRepo.countUsersByState(UsersStates.ACTIVE);
		System.out.println(activeUsers);
		long inactiveUsers = userRepo.countUsersByState(UsersStates.INACTIVE);
		System.out.println(inactiveUsers);
		long loggedLast24H = userRepo.countUsersByLastLogin(new Date().getTime()- Constants.ONE_DAY_MILLIS, new Date().getTime());
		System.out.println(loggedLast24H);
		long admins = userRepo.countUsersByRole(Role.ROLE_ADMINISTRATOR.toString());
		System.out.println(admins);
		long storeManagers = userRepo.countUsersByRole(Role.ROLE_STOREMANAGER.toString());
		System.out.println(storeManagers);
		long officers = userRepo.countUsersByRole(Role.ROLE_OFFICER.toString());
		System.out.println(officers);
		long blockedBySystem = userRepo.countUsersByState(UsersStates.BLOCKED_BY_SYSTEM);
		System.out.println(blockedBySystem);
		long deleted = userRepo.countUsersByState(UsersStates.DELETED);
		System.out.println(deleted);


		//long total, long activeUsers, long inactiveUsers, long loggedLast24Hours, long blockedBySystem, long deleted, long administrators, long storeManagers, long officers
		UserReport report = new UserReport(total,activeUsers,inactiveUsers,loggedLast24H,blockedBySystem,deleted,admins,storeManagers,officers);

		return report;
	}
	//############## ALTER METHODS ###############


	/**
	 * this method is used to update the user attempts
	 * @param attempts the number of new attempts
	 * @param username the user to update
	 */
	@PatchMapping("/user/patch/loginattempts/update/{attempts}/{username}")
    public void changeUserAttemptsUPDATE(@PathVariable int attempts, @PathVariable String username){
		userRepo.updateUserLoginAttempts(attempts,username);
	}

	/**
	 * this method is used to change user login attempts
	 * @param attempts the number of new attempts to set
	 * @param username the user to update
	 */
	@PatchMapping("/user/patch/loginattempts/set/{attempts}/{username}")
	public void changeUserAttempts(@PathVariable int attempts, @PathVariable String username){
		userRepo.updateUserLoginAttempts(attempts,username);
	}

	/**
	 * this method is used to patch the login attempts
	 * @param username the user to update
	 * @return the response
	 */
	@PatchMapping("/user/patch/loginattempts/increaseone/{username}")
	public String increaseUserAttempts(@PathVariable String username){
		UserDAO user = userRepo.findByUsername(username);

		userRepo.updateUserLoginAttempts(user.getLoginAttempts()+1,username);
		user.setLoginAttempts(user.getLoginAttempts()+1);

		if (user.getLoginAttempts()==4){
			userRepo.updateUserActiveUserByUsername(UsersStates.INACTIVE,username);
			return "user deactivated: too may login attempts.";
		}

		return "user login attempts increased.\nuser login attempts: "+user.getLoginAttempts();
	}

	/**
	 * this method is used to restore an inactive user
	 * @param username the user to restore
	 * @return the restored user
	 */
	@PatchMapping("/user/patch/restoreuser/{username}")
	public UserDAO restoreUser(@PathVariable String username){
		userRepo.updateUserActiveUserByUsername(UsersStates.ACTIVE,username);
		userRepo.updateUserLoginAttempts(0,username);
		return userRepo.findByUsername(username);
	}

	/**
	 * this method is used to reset an user's password
	 * HTTP PATCH /user/patch/resetpassword/username/{username}
	 * @param username the user to reset password
	 * @return the user with the reset password
	 */
	@PatchMapping("/user/patch/resetpassword/username/{username}")
	public UserDAO resetPasswordByUser(@PathVariable String username){
		String tempass = Password.generatePassword(10);
		userRepo.updateUserTempPasswordByUsername(tempass,username);
		userRepo.updateUserPasswordByUsername(Password.getBCrypt(tempass),username);

		//il return puo essere tolto
		return this.getUserByUsername(username);
	}


	/**
	 * this method is used to update an user's password
	 * @param username the user to update the password
	 * @param newpassword the new password to update
	 * @return the updated user
	 */
	@PatchMapping("/user/patch/updatepassword/username/{username}/{newpassword}")
	public UserDAO updateUserPassword(@PathVariable String username, @PathVariable String newpassword){
		userRepo.updateUserPasswordByUsername(Password.getBCrypt(newpassword),username);
		userRepo.updateUserTempPasswordByUsername("",username);

		//il return puo essere tolto
		return userRepo.findByUsername(username);
	}


    //===============POST METHODS


	/**
	 * this method is used to register a new user
	 * @param newUser the user to exchange
	 * @return the updated user
	 * @throws Exception if the password is not valid
	 */
	@PostMapping("/register")
    public UserDAO createUser(@RequestBody UserDAO newUser) throws Exception {

	   try {
	            User tempUserObj = new User(
	                    newUser.getUsername(),                // HTTP username
	                    newUser.getPassword(),                // HTTP plainTextPassword
	                    Role.valueOf(newUser.getUserRole()) // HTTP Role
				);
	            
	            //User tempUserObj = User.getUser(newUser); for future implementations
	            UserDAO saveBean = tempUserObj.getUserDAO();
	            
				   saveBean.setCreationDate(new Date().getTime());
				   saveBean.setEmail(newUser.getEmail());
				   saveBean.setLastAccess(Constants.TIMESTAMP1900);
				   saveBean.setLoginAttempts(0);
				   saveBean.setActiveUser(UsersStates.ACTIVE);

	            return userRepo.save(saveBean);
		}catch (Exception e) {
			e.printStackTrace();
	        throw new Exception(e.getMessage()
	        		+ "1) Your password must be between 8 and 30 characters."
	        		+ "2) Your password must contain at least one uppercase, or capital, letter (ex: A, B, etc.)"
	                + "3) Your password must contain at least one lowercase letter."
	                + "4) Your password must contain at least one number digit (ex: 0, 1, 2, 3, etc.)"
	                + "5) Your password must contain at least one special character -for example: $, #, @, !,%,^,&,*");

		}

    }

    //===============PUT METHODS
    /*
    @PutMapping("/editUser")
	public UserBean editUser(@RequestBody UserBean newUser) {
    	// controlla se l'utente esiste
    	// controlla i dati inseriti mediante la classe User
    	// salva nel database
		User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getPassword(),		// HTTP plainTextPassword
				Role.valueOf(newUser.getUserRole()) // HTTP Role
				);

	   tempUserObj.setEmail(newUser.getEmail());
		UserBean saveBean = tempUserObj.getUserBean();
		//saveBean.setCreationDate(null);
		saveBean.setCreationDate(new Date());
		saveBean.setEmail("ricciuto99@gamail.com");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println(saveBean.getEmail());
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		return userRepo.save(newUser);
	}
	*/

	/**
	 * this method is used to update an user's username
	 * @param id the id used to search the user
	 * @param username the new username
	 * @return the updated user
	 */
	@PatchMapping("/user/update/username/id/{id}/{username}")
	public UserDAO updateUserUsernameByID(@PathVariable long id, @PathVariable String username){
		userRepo.updateUserUsernameByID(username,id);

		return this.getUserByUsername(username);//the new username
	}


	/**
	 * this method is used to change an user's password
	 * @param id the id used to search the user
	 * @param temppass the new password
	 * @return the updated user
	 */
	@PatchMapping("/user/update/temppass/id/{id}/{temppass}")
	public UserDAO updateUserTemppassByID(@PathVariable long id, @PathVariable String temppass){
		userRepo.updateUserTemppassByID(temppass,id);
		userRepo.updateUserPasswordByID(Password.getBCrypt(temppass),id);
		return userRepo.getById(id);
	}

	/**
	 * this method is used to update an user's email
	 * @param id the id used to serach the user
	 * @param email the new email
	 * @return the updated user
	 */
	@PatchMapping("/user/update/email/id/{id}/{email}")
	public UserDAO updateUserEmailByID(@PathVariable long id, @PathVariable String email){
		userRepo.updateUserEmailByID(email,id);

		return userRepo.getById(id);
	}

	/**
	 * this method is used to update an user's role
	 * @param id the id used to search the user
	 * @param role the new role
	 * @return the updated user
	 */
	@PatchMapping("/user/update/email/id/{id}/{role}")
	public UserDAO updateUserRoleByID(@PathVariable long id, @PathVariable String role){
		userRepo.updateUserRoleByID(Role.valueOf(role).toString(),id);

		return userRepo.getById(id);
	}


	/**
	 * this method is used to update an user's attempt
	 * @param id the id used to search the user
	 * @param attempts the new attempts
	 * @return the updated user
	 */
	@PatchMapping("/user/update/attempts/id/{id}/{attempts}")
	public UserDAO updateUserLoginAttemptsByID(@PathVariable long id, @PathVariable int attempts){
		userRepo.updateUserLoginAttemptsByID(attempts,id);
		return userRepo.getById(id);
	}
	//updateUserActiveUserByID

	/**
	 * this method is used to update an user's state
	 * @param id the id used to search the user
	 * @param active the new state
	 * @return the updated user
	 */
	@PatchMapping("/user/update/activeuser/id/{id}/{active}")
	public UserDAO updateUserActiveUserByID(@PathVariable long id, @PathVariable short active){
		userRepo.updateUserLoginAttemptsByID(active,id);

		return userRepo.getById(id);
	}


	/**
	 * this method is used to update an entire user with a new one
	 * @param newuser the all new user
	 * @return the updated user
	 * update user
	 * 	 * username, temppass -> password, email, ruolo, login attempts, activeuser
	 */
	@PostMapping("/user/update")
	public UserDAO updateUserByID(@RequestBody UserDAO newuser){
		this.updateUserUsernameByID(		newuser.getIdUser(), newuser.getUsername()							);
		this.updateUserTemppassByID(		newuser.getIdUser(), newuser.getTemppass()							);
		this.updateUserEmailByID(			newuser.getIdUser(), newuser.getEmail()								);
		this.updateUserRoleByID(			newuser.getIdUser(), newuser.getUserRole()							);
		this.updateUserLoginAttemptsByID(	newuser.getIdUser(), Integer.valueOf(newuser.getLoginAttempts())	);
		this.updateUserActiveUserByID(		newuser.getIdUser(), newuser.getActiveUser()						);

		return userRepo.getById(newuser.getIdUser());
	}

    
    //===============DELETE METHODS by fields

	/**
	 * BEFORE:
	 * {
	 *         "idUser": 3,
	 *         "username": "antonellino",
	 *         "password": "$2a$10$PRTZAxBCVhb.h.8jqXEU4.wuhv6v2HSnBbFwYXjzLw.Uc0OQHOMdK",
	 *         "temppass": "",
	 *         "email": "antonello99@gmail.com",
	 *         "userRole": "ROLE_OFFICER",
	 *         "creationDate": 1625405342438,
	 *         "lastAccess": 1625405342438,
	 *         "loginAttempts": 0,
	 *         "activeUser": 1
	 *     }*/

	/**
	 * AFTER:
	 * {
	 *         "idUser": 3,
	 *         "username": "giuseppolino",
	 *         "password": "$2a$10$PRTZAxBCVhb.h.8jqXEU4.wuhv6v2HSnBbFwYXjzLw.Uc0OQHOMdK",
	 *         "temppass": "Succhiamelo99+",
	 *         "email": "antonello99@gmail.com",
	 *         "userRole": "ROLE_ADMINISTRATOR",
	 *         "creationDate": 1625405342438,
	 *         "lastAccess": 1625405342438,
	 *         "loginAttempts": 1,
	 *         "activeUser": 1
	 * }*/

	/**
	 * this method is used to delete an user by username
	 * @param username the username used to search the user to delete
	 * @return the updated user
	 */
    @DeleteMapping("/user/delete/username/{username}")
	public UserDAO deleteUserByUsername(@PathVariable String username) {
		userRepo.updateUserActiveUserByUsername(UsersStates.DELETED, username);
		//puo essere eliminato se non ci interessa ritornare l'user modificato
		return this.getUserByUsername(username);
	}


	/**
	 * this method is used to delete an user by username
	 * @param email the email used to search the user to delete
	 * @return the updated user
	 */
	@DeleteMapping("/user/delete/email/{email}")
	public UserDAO deleteUserByEmail(@PathVariable String email) {
		userRepo.updateUserActiveUserByEmail(UsersStates.DELETED, email);
		return this.getUserByEmail(email);
	}

	/**
	 * this method is used to get the user only if the provided token belongs to the user
	 * @param token the user token
	 * @param username the user username
	 * @return the UserDAO
	 * @throws ParseException if the token is wrong
	 * @throws UserException if the token does not belong to the user
	 */
	@GetMapping("/user/get/username/token/{token}/{username}")
	private UserDAO getUserFromToken(@PathVariable String token, @PathVariable String username) throws ParseException, UserException {
		String[] chunks = token.split("\\.");

		Base64.Decoder decoder = Base64.getDecoder();
		String payload = new String(decoder.decode(chunks[1]));

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(payload);

		if (!json.get("sub").toString().equals(username))
			throw new UserException("The token does not belong to the provided user.");
		return this.getUserByUsername(username);
	}



	// ########## ADMIN STUFF ##############
	/**
	 * this method is very dangerous!
	 * it should be deleted from the final version.*/
	@PatchMapping("/user/setPasswords")
	public JSONObject setUserPasswords(){
    	Iterable<UserDAO> users = this.getAllUsers();
		Random random = new Random();

		long finalPasswordsUpdated = 0;
		long tempPasswordsUpdated = 0;

    	for (UserDAO userBean : users){
    		UserDAO currentUser = userBean;
			userRepo.delete(userBean);

    		if (random.nextInt(100) > 40){
    			userBean.setTemppass("");
				userBean.setPassword(Password.getBCrypt(userBean.getPassword()));
				userRepo.save(userBean);

				finalPasswordsUpdated+=1;
			}else {
    			userBean.setPassword(Password.getBCrypt(userBean.getTemppass()));
    			userRepo.save(userBean);
    			tempPasswordsUpdated+=1;
			}
		}

		JSONObject report = new JSONObject();
    	report.put("encrypted passwords" , finalPasswordsUpdated);
		report.put("temp passowrds" , tempPasswordsUpdated);

		return report;
	}
    
}
