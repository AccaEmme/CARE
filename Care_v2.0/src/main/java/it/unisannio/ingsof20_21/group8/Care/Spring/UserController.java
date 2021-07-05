/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    //===============GET METHODS
    @GetMapping("/user")
	public UserDAO/*Iterable<UserBean>*/ testGetUser() {
    	UserDAO ub = new UserDAO();
    	ub.setUsername("ciccioGiuliano");
    	ub.setPassword("ciaccioLuigi");
    	//Iterable<UserBean> i = new Iterable<UserBean>();
    	//i.add(ub);
    	//return i;
    	ub.setCreationDate(new Date());
    	ub.setLastAccess(new Date());
    	return ub;
	}

	/**
	 * 127.0.0.1:8087/user/get/all
	 * */
	@GetMapping("/user/get/all")
	public Iterable<UserDAO> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	 * 127.0.0.1:8087/user/get/email/dtarver6@simplemachines.org
	 * */
	@GetMapping("/user/get/email/{email}")
	public UserDAO getUserByEmail(@PathVariable String email){
		return userRepo.findByEmail(email);
	}

    /*
      login():attempts
     */

    /**
	 * 127.0.0.1:8087/user/get/username/dedland7
	 * */
	@GetMapping("/user/get/username/{username}")
	public UserDAO getUserByUsername(@PathVariable String username){
		return userRepo.findByUsername(username);
	}

	/**
	 * 127.0.0.1:8087/user/get/role/Administrator
	 * */
	@GetMapping("/user/get/role/{role}")
	public Iterable<UserDAO> getUserByRole(@PathVariable String role){
		return userRepo.findUserByRole(role);
	}

	/**
	 * 127.0.0.1:8087/user/get/created/1624217670000/1624995270000
	 * */
	@GetMapping("user/get/created/{firstdate}/{seconddate}")
	public Iterable<UserDAO> getUserCreatedBetween(@PathVariable long firstdate, @PathVariable long seconddate){
		return userRepo.findCreatedBetween(firstdate,seconddate);
	}

	@GetMapping("user/get/active")
	public Iterable<UserDAO> getActiveUsers(){
		return userRepo.filterUsersByState(UsersStates.ACTIVE);
	}
	@GetMapping("user/get/inactive")
	public Iterable<UserDAO> getInactiveUsers(){
		return userRepo.filterUsersByState(UsersStates.INACTIVE);
	}




	/**
	 * HTTP request: 127.0.0.1:8087/user/report
	 * Output:
	 {
	      "total": 1000,
	      "activeUsers": 482,
	      "loggedLast24Hours": 2,
	      "administrators": 482,
	      "storeManagers": 322,
	      "officers": 340,
	      "deactivatedUsers": 518
	  }*/
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
	/*
	non riesco a far funzionare la query di UPDATE*/

	@PatchMapping("/user/patch/loginattempts/update/{attempts}/{username}")
    public void changeUserAttemptsUPDATE(@PathVariable int attempts, @PathVariable String username){
		userRepo.updateUserLoginAttempts(attempts,username);
	}

	@PatchMapping("/user/patch/loginattempts/set/{attempts}/{username}")
	public void changeUserAttempts(@PathVariable int attempts, @PathVariable String username){
		userRepo.updateUserLoginAttempts(attempts,username);
	}

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

	@PatchMapping("/user/patch/restoreuser/{username}")
	public UserDAO restoreUser(@PathVariable String username){
		userRepo.updateUserActiveUserByUsername(UsersStates.ACTIVE,username);
		userRepo.updateUserLoginAttempts(0,username);
		return userRepo.findByUsername(username);
	}

	@PatchMapping("/user/patch/resetpassword/username/{username}")
	public UserDAO resetPasswordByUser(@PathVariable String username){
		String tempass = Password.generatePassword(8);
		userRepo.updateUserTempPasswordByUsername(tempass,username);
		userRepo.updateUserPasswordByUsername(Password.getBCrypt(tempass),username);

		//il return puo essere tolto
		return this.getUserByUsername(username);
	}
	@PatchMapping("/user/patch/updatepassword/username/{username}/{newpassword}")
	public UserDAO updateUserPassword(@PathVariable String username, @PathVariable String newpassword){
		userRepo.updateUserPasswordByUsername(Password.getBCrypt(newpassword),username);
		userRepo.updateUserTempPasswordByUsername("",username);

		//il return puo essere tolto
		return userRepo.findByUsername(username);
	}


    //===============POST METHODS
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
				   saveBean.setLastAccess( new Date().getTime() );
				   saveBean.setLoginAttempts(0);
				   saveBean.setActiveUser(UsersStates.ACTIVE);

	            return userRepo.save(saveBean);
		}catch (Exception e) {
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

	@PatchMapping("/user/update/username/id/{id}/{username}")
	public UserDAO updateUserUsernameByID(@PathVariable long id, @PathVariable String username){
		userRepo.updateUserUsernameByID(username,id);

		return this.getUserByUsername(username);//the new username
	}


	@PatchMapping("/user/update/temppass/id/{id}/{temppass}")
	public UserDAO updateUserTemppassByID(@PathVariable long id, @PathVariable String temppass){
		userRepo.updateUserTemppassByID(temppass,id);
		userRepo.updateUserPasswordByID(Password.getBCrypt(temppass),id);
		return userRepo.getById(id);
	}

	@PatchMapping("/user/update/email/id/{id}/{email}")
	public UserDAO updateUserEmailByID(@PathVariable long id, @PathVariable String email){
		userRepo.updateUserEmailByID(email,id);

		return userRepo.getById(id);
	}

	@PatchMapping("/user/update/email/id/{id}/{role}")
	public UserDAO updateUserRoleByID(@PathVariable long id, @PathVariable String role){
		userRepo.updateUserRoleByID(Role.valueOf(role).toString(),id);

		return userRepo.getById(id);
	}

	@PatchMapping("/user/update/attempts/id/{id}/{attempts}")
	public UserDAO updateUserLoginAttemptsByID(@PathVariable long id, @PathVariable int attempts){
		userRepo.updateUserLoginAttemptsByID(attempts,id);

		return userRepo.getById(id);
	}
	//updateUserActiveUserByID

	@PatchMapping("/user/update/activeuser/id/{id}/{active}")
	public UserDAO updateUserActiveUserByID(@PathVariable long id, @PathVariable short active){
		userRepo.updateUserLoginAttemptsByID(active,id);

		return userRepo.getById(id);
	}

	/**update user
	 * username, temppass -> password, email, ruolo, login attempts, activeuser*/

	@PostMapping("/user/update")
	public UserDAO updateUserByID(@RequestBody UserDAO newuser){
		this.updateUserUsernameByID(newuser.getIdUser(), newuser.getUsername());
		this.updateUserTemppassByID(newuser.getIdUser(),newuser.getTemppass());
		this.updateUserEmailByID(newuser.getIdUser(),newuser.getEmail());
		this.updateUserRoleByID(newuser.getIdUser(),newuser.getUserRole());
		this.updateUserLoginAttemptsByID(newuser.getIdUser(),newuser.getLoginAttempts());
		this.updateUserActiveUserByID(newuser.getIdUser(),newuser.getActiveUser());

		return userRepo.getById(newuser.getIdUser());
	}
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
    
    //===============DELETE METHODS by fields
    @DeleteMapping("/user/delete/username/{username}")
	public UserDAO deleteUserByUsername(@PathVariable String username) {
		userRepo.updateUserActiveUserByUsername(UsersStates.DELETED, username);
		//puo essere eliminato se non ci interessa ritornare l'user modificato
		return this.getUserByUsername(username);
	}
	@DeleteMapping("/user/delete/email/{email}")
	public UserDAO deleteUserByEmail(@PathVariable String email) {
		userRepo.updateUserActiveUserByEmail(UsersStates.DELETED, email);
		return this.getUserByEmail(email);
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
