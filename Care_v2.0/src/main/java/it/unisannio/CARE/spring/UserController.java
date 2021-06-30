/**
 * 
 */
package it.unisannio.CARE.spring;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.web.bind.annotation.*;

import it.unisannio.CARE.model.user.Role;
import it.unisannio.CARE.model.user.User;



@CrossOrigin("*")
@RestController
@Consumes("application/json")
@Produces("application/json")
//@Path("rest")
public class UserController implements ContainerResponseFilter {
    private final UserRepository userRepo;
    
    public UserController(UserRepository userRepo) {
    	this.userRepo = userRepo;
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // TODO Auto-generated method stub
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }


    //===============GET METHODS
    @GetMapping("/user")
	public UserBean/*Iterable<UserBean>*/ testGetUser() {
    	UserBean ub = new UserBean();
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
	public Iterable<UserBean> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	 * 127.0.0.1:8087/user/get/email/dtarver6@simplemachines.org
	 * */
	@GetMapping("/user/get/email/{email}")
	public UserBean getUserByEmail(@PathVariable String email){
		return userRepo.findByEmail(email);
	}
    
    /*
      login():attempts
     */

    /**
	 * 127.0.0.1:8087/user/get/username/dedland7
	 * */
	@GetMapping("/user/get/username/{username}")
	public UserBean getUserByUsername(@PathVariable String username){
		return userRepo.findByUsername(username);
	}

	/**
	 * 127.0.0.1:8087/user/get/role/Administrator
	 * */
	@GetMapping("/user/get/role/{role}")
	public Iterable<UserBean> getUserByRole(@PathVariable String role){
		return userRepo.findUserByRole(role);
	}

	/**
	 * 127.0.0.1:8087/user/get/created/1624217670000/1624995270000
	 * */
	@GetMapping("user/get/created/{firstdate}/{seconddate}")
	public Iterable<UserBean> getUserCreatedBetween(@PathVariable long firstdate, @PathVariable long seconddate){
		return userRepo.findCreatedBetween(firstdate,seconddate);
	}

	//############## ALTER METHODS ###############
	/*
	non riesco a far funzionare la query di UPDATE
	@PatchMapping("/user/patch/loginattempts/{attempts}/{username}")
    public void changeUserAttempts(@PathVariable int attempts, @PathVariable String username){
		userRepo.updateUserLoginAttempts(attempts,username);
	}*/

	@PatchMapping("/user/patch/loginattempts/set/{attempts}/{username}")
	public void changeUserAttempts(@PathVariable int attempts, @PathVariable String username){
		UserBean user = userRepo.findByUsername(username);

		userRepo.delete(user);
		user.setLoginAttempts(attempts);
		userRepo.save(user);
	}

	@PatchMapping("/user/patch/loginattempts/increaseone/{username}")
	public String increaseUserAttempts(@PathVariable String username){
		UserBean user = userRepo.findByUsername(username);

		userRepo.delete(user);
		user.setLoginAttempts(user.getLoginAttempts()+1);

		if (user.getLoginAttempts()==4){
			//deactivate user
			user.setActiveUser(false);

			userRepo.save(user);
			return "user deactivated: too may login attempts.";
		}

		userRepo.save(user);

		return "user login attempts increased.\nuser login attempts: "+user.getLoginAttempts();
	}

	@PatchMapping("/user/patch/restoreuser/{username}")
	public UserBean restoreUser(@PathVariable String username){
		UserBean user = userRepo.findByUsername(username);

		userRepo.delete(user);
		user.setActiveUser(true);
		user.setLoginAttempts(0);
		userRepo.save(user);

		return user;
	}


    //===============POST METHODS
    @PostMapping("/user/adduser")
    /**
     * createUser
     * 
     * Method for add a new user
     * Example1: empty password, so will be generated automatically
     * Request:
      	{
	  		"username": "Hermann",
	  		"password": "",
	  		"email": "hermann@care.it",
	  		"userRole": "Administrator"
		}
     * 
     * Response:
     	{
			"idUser": 21,
			"username": "Hermann",
			"password": "25B9BC299D79C547B92D6D576B2CE15F",
			"temppass": "nA#9VqaE0R",
			"email": "hermann@care.it",
			"userRole": "Administrator",
			"creationDate": "2021-06-28",
			"lastAccess": "1900-01-01",
			"loginAttempts": 0,
			"activeUser": false
		}
     * 
     * Example2: giving a password as input
     * 
     * Request:
     * {
	  		"username": "Luigi",
	  		"password": "Luigi4@",
	  		"email": "gigi@care.it",
	  		"userRole": "Administrator"
		}
     * Response:
		{
			"idUser": 22,
			"username": "Luigi",
			"password": "E008FF4282CDEF5A4BE6CB40AB73A5C8",
			"temppass": "",
			"email": "gigi@care.it",
			"userRole": "Administrator",
			"creationDate": "2021-06-28",
			"lastAccess": "1900-01-01",
			"loginAttempts": 0,
			"activeUser": false
		}
     * @param newUser   - UserBean object from HTTP request data
     * @return UserBean - UserBean object after User parsing that returns a valid checked UserBean to store in database.
     */
    // {username}/plainTextPassword/{plainTextPassword}/role/{role}
	public UserBean createUser(@RequestBody UserBean newUser) {
        	
    	//try {
			User tempUserObj = new User(
					newUser.getUsername(),				// HTTP username
					newUser.getPassword(),				// HTTP plainTextPassword
					Role.valueOf(newUser.getUserRole()) // HTTP Role
					);
		    
			UserBean saveBean = tempUserObj.getUserBean();
			saveBean.setCreationDate(new Date());
			saveBean.setEmail(newUser.getEmail());
			saveBean.setLastAccess( -2_208_988_800_000L );
			return userRepo.save(saveBean);
    	 //} catch(/*UserControllerNotValidValueException*/ Exception e) {
    	//	System.err.println("UserController.java: "+e);
    	//	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "adduser Error x Luigi", e);
    	//	 throw new Exception("Verify sent datas");
    	//}
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
    
    //===============DELETE METHODS
    @DeleteMapping("/deleteUser")
	public void deleteUser(@RequestBody UserBean deleteUser) {
		//userRepo.logicalDelete(deleteUser); /* TODO: sarebbe utile una cancellazione logica e non fisica dal DB */
    	deleteUser.setActiveUser(false);
    	userRepo.save(deleteUser);
	}
    
}
