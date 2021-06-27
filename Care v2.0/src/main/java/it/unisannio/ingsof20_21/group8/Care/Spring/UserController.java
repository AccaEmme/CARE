/**
 * 
 */
package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
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
import it.unisannio.CARE.Model.Util.Password;


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
    
    
    
	@GetMapping("/user/{username}")
	public Iterable<UserBean> getNotesbytitle(@PathVariable String username){
		return userRepo.findByUsername(username); /*.orElseThrow();*/
}
    
    //===============POST METHODS
    @PostMapping("/user/addUser/")
    // {username}/plainTextPassword/{plainTextPassword}/role/{role}
	public UserBean createUser(@RequestBody UserBean newUser) {
    	if(newUser.getPassword().equals("")) newUser.setPassword( Password.generatePassword(12) );  
		User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getPassword(),				// HTTP plainTextPassword
				Role.valueOf(newUser.getUserRole()) // HTTP Role
				);
	     
		UserBean saveBean = tempUserObj.getUserBean();
		//saveBean.setCreationDate(null);
		saveBean.setCreationDate(new Date());
		saveBean.setEmail("ricciuto45@gmail.com");
		saveBean.setLastAccess(new Date());
		return userRepo.save(saveBean);
	}
    
    //===============PUT METHODS
  /*  @PutMapping("/editUser")
	public UserBean editUser(@RequestBody UserBean newUser) {
	/*	User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getHiddenPassword(),		// HTTP plainTextPassword
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
	}*/
    
    //===============DELETE METHODS
    @DeleteMapping("/deleteUser")
	public void deleteUser(@RequestBody UserBean deleteUser) {
		//userRepo.logicalDelete(deleteUser); /* TODO: sarebbe utile una cancellazione logica e non fisica dal DB */
    	deleteUser.setActiveUser(false);
    	userRepo.save(deleteUser);
	}
    
}
