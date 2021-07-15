package it.unisannio.ingsof20_21.group8.Care.controller;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import it.unisannio.CARE.model.user.UsersStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.spring.bean.AuthenticationRequestBean;
import it.unisannio.CARE.spring.bean.AuthenticationResponseBean;
import it.unisannio.ingsof20_21.group8.Care.Spring.AuthenticationRepository;
import it.unisannio.ingsof20_21.group8.Care.Spring.CustomUserDetailsService;
import it.unisannio.ingsof20_21.group8.Care.Spring.JwtUtil;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;

/*metodi per registrazione e autenticazione*/

/**
 * this class is used to manage the user's authentication
 */
@CrossOrigin("*")
@RestController
public class AuthenticationController {

	private final AuthenticationRepository userRepo;

	/**
	 * @param userRepo the user repository interface
	 */
	public AuthenticationController(AuthenticationRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * @param authenticationRequest the request
	 * @return the response
	 * @throws Exception if the username or password are wrong
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestBean authenticationRequest)
			throws Exception {
		// controlli
		UserDAO userCheck = userRepo.getUserDaoFromUsername(authenticationRequest.getUsername());
		if (userCheck == null)
			return new ResponseEntity<String>("Couldn't find the user.", HttpStatus.NOT_FOUND);
		if (userCheck.getActiveUser() == UsersStates.INACTIVE)
			return new ResponseEntity<String>("User inactive: Too many login attempts.",
					HttpStatus.UNPROCESSABLE_ENTITY);

		// se l'user esiste AND ha tentativi di accesso < 3 allora procedo
		// all'autenticazione
		try {
			// se l'autenticazione non lancia eccezioni (le password coincidono)...
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword() + Constants.PASSWORD_SALT));

		} catch (AuthenticationException e) {
			// autenticazione fallita
			e.printStackTrace();
			userRepo.updateUserLoginAttempts(userCheck.getLoginAttempts() + 1, userCheck.getUsername());

			// bisogna sempre pescare dal database per avere dati aggiornati
			if (userRepo.getUserDaoFromUsername(authenticationRequest.getUsername()).getLoginAttempts() > 2) {
				// marco l'user come inattivo
				userRepo.updateUserActiveUserByUsername(UsersStates.INACTIVE, authenticationRequest.getUsername());
			}

			// importantissimo perche se non si lancia l'eccezione viene restituito il
			// token!
			// a questo punto dico solo che la password è sbagliata, perchè ho gia
			// controllato l'esistenza dell'username.
			return new ResponseEntity<String>("Wrong Password.", HttpStatus.NOT_ACCEPTABLE);
		}
		// ...allora aggiorno l'ultimo accesso ed azzero eventuali tentativi di accesso
		// precedenti.

		userRepo.updateAccess(authenticationRequest.getUsername(), (new Date()).getTime());
		// azzero i tentativi di accesso perchè autenticato.
		userRepo.updateUserLoginAttempts(0, authenticationRequest.getUsername());
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);

		// ritorno il token
		return ResponseEntity.ok(new AuthenticationResponseBean(token));
	}
	/*
	 * @RequestMapping(value = "/register", method = RequestMethod.POST) public
	 * ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
	 * return ResponseEntity.ok(userDetailsService.save(user)); }
	 */
}
