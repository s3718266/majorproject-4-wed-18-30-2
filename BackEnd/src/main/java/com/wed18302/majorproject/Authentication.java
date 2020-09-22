package com.wed18302.majorproject;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wed18302.majorproject.model.User;
import com.wed18302.majorproject.model.UserRepository;
import com.wed18302.majorproject.model.UserType;

public class Authentication {

	public static final String INSUFFICIENT_PERMISSIONS = generateErrorJson("Insufficient privilliges.");
	
    @Autowired
    private UserRepository userRepository;
    
    public static final String SECRET = "WED18302JWTSECRET";
    public static final long EXPIRATION_SECONDS = 86400 * 32; // 32 days
    
	public static String generateTokenFromUser(User auth) {
		String token = JWT.create()
                .withSubject(auth.getEmail())
                .withIssuer("wed18302")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_SECONDS))
                .sign(Algorithm.HMAC256(SECRET.getBytes()));
		return token;
	}
	
	// returns the token if correct otherwise null
	public static String decodeToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("wed18302")
		        .build();
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt.getSubject();
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User authenticate(HttpServletRequest request, UserType permissionLevel) {
    	String token = request.getParameter("auth-token");
    	String email = decodeToken(token);
    	if (email == null)
    		return null;
    	
    	User user = userRepository.findByEMAIL(email);
    	if (user.hasPermission(permissionLevel))
    		return user;
    	
    	return null;
	}
	
	public static String generateTokenJson(User auth) {
		return formatTokenJson(generateTokenFromUser(auth));
	}
	
	public static String formatTokenJson(String token) {
		return "{\"auth-token\":\"" + token + "\"}";
	}
	
	public static String generateErrorJson(String error) {
		return "{\"error\":\"" + error + "\"}";
	}
	
}
