/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.Model.User.Role;
import it.unisannio.CARE.Model.User.User;


/**
 * @author AccaEmme on 2021-06-26
 *
 */

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

	@GetMapping("/user/getall")
	public Iterable<UserBean> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/user/get/email/{email}")
	public UserBean getUserByEmail(@PathVariable String email){
		return userRepo.findByEmail(email);
	}
    
    /*
      login():attempts
     */
    
	@GetMapping("/user/get/username/{username}")
	public Iterable<UserBean> getUserByUsername(@PathVariable String username){
		return userRepo.findByUsername(username); /*.orElseThrow();*/
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
