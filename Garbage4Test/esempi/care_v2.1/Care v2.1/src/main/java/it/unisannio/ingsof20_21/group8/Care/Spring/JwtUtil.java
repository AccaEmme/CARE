package it.unisannio.ingsof20_21.group8.Care.Spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.unisannio.CARE.Model.User.Role;


/*serve a settare il token, tempo sessione e ruolo*/

@Service
public class JwtUtil {

	private String secret;
	private int jwtExpirationInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

		if (roles.contains(new SimpleGrantedAuthority(Role.ROLE_ADMINISTRATOR.toString()))) {
			claims.put("is"+Role.ROLE_ADMINISTRATOR.toString() , true);
		}
		if (roles.contains(new SimpleGrantedAuthority(Role.ROLE_OFFICER.toString()))) {
			claims.put("is" +Role.ROLE_OFFICER.toString(), true);
		}
		if (roles.contains(new SimpleGrantedAuthority(Role.ROLE_STOREMANAGER.toString()))) {
			claims.put("is" + Role.ROLE_STOREMANAGER.toString(), true);
		}
	
	
		
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}

	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		List<SimpleGrantedAuthority> roles = null;

		Boolean isAdministrator = claims.get("is"+Role.ROLE_ADMINISTRATOR.toString() , Boolean.class);
		Boolean isOfficer = claims.get("is" +Role.ROLE_OFFICER.toString(), Boolean.class);
		Boolean isStoremanager = claims.get("is" + Role.ROLE_STOREMANAGER, Boolean.class);

		if (isAdministrator != null && isAdministrator) {
			roles = Arrays.asList(new SimpleGrantedAuthority(Role.ROLE_ADMINISTRATOR.toString()));
		}

		if (isOfficer != null && isOfficer) {
			roles = Arrays.asList(new SimpleGrantedAuthority(Role.ROLE_OFFICER.toString()));
		}
		if (isStoremanager != null && isStoremanager) {
			roles = Arrays.asList(new SimpleGrantedAuthority(Role.ROLE_STOREMANAGER.toString()));
		}
		return roles;

	}

}