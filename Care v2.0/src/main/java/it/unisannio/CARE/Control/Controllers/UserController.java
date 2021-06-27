/**
 * 
 */
package it.unisannio.CARE.Control.Controllers;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.Control.Interfaces.UserRepository;
import it.unisannio.CARE.Model.Beans.UserBean;
import it.unisannio.CARE.Model.User.Role;
import it.unisannio.CARE.Model.User.User;


/**
 * @author AccaEmme on 2021-06-26
 *
 */

@CrossOrigin("*")
@RestController
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
	public UserBean testGetUser() {
    	UserBean ub = new UserBean();
    	ub.setUsername("ciccio");
    	ub.setHiddenPassword("ciaccio");
    	return ub;
	}
    
    //===============POST METHODS
    @PostMapping("/addUser")
	public UserBean createUser(@RequestBody UserBean newUser) {
		User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getHiddenPassword(),		// HTTP plainTextPassword
				Role.valueOf(newUser.getUserRole()) // HTTP Role
				);
	
		UserBean saveBean = tempUserObj.getUserBean();
		//saveBean.setCreationDate(null);
		
		return userRepo.save(saveBean);
	}
    
    //===============PUT METHODS
    @PutMapping("/editUser")
	public UserBean editUser(@RequestBody UserBean newUser) {
		User tempUserObj = new User(
				newUser.getUsername(),				// HTTP username
				newUser.getHiddenPassword(),		// HTTP plainTextPassword
				Role.valueOf(newUser.getUserRole()) // HTTP Role
				);
	
		UserBean saveBean = tempUserObj.getUserBean();
		//saveBean.setCreationDate(null);
		
		return userRepo.save(saveBean);
	}
    
    //===============DELETE METHODS
    @DeleteMapping("/deleteUser")
	public void deleteUser(@RequestBody UserBean deleteUser) {
		userRepo.delete(deleteUser); /* TODO: sarebbe utile una cancellazione logica e non fisica dal DB */
	}
    
}
