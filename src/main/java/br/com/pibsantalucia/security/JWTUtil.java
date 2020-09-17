package br.com.pibsantalucia.security;

import br.com.pibsantalucia.util.JwtExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

	private static String key = "MIIBOgIBAAJBAJz0CskFMq9/5SOs1iSFP2oEwXIKERxAiiV2deicMWpvgERTdqky\n" + 
			"CREbbrocX0tDelsk50T6zF7cDsomHGJAOO0CAwEAAQJAbuqfvJOp0BCH9AAujTyF\n" + 
			"Jf0aX+bxVI/fkL34cVwAKABqNd0zK0oRhwoakZA6nAE+9Z5Sir+/8X4+SndPgtcP\n" + 
			"wQIhAOVkv/GCa/Z/9ZG/7+N2RLyceh87YUVvxGhgcYNswA7VAiEAryhdjhpwyjpp\n" + 
			"oeETFujtkUbj9PKrivkj7Bl7UmIZ/bkCIF+r3lXWhDqhcYThHSar280iIdjGxjHU\n" + 
			"hIDXwGAEyjElAiAWQgLhc+qxSAr0Ty2l06Ocum1tSwkRd+dgsTxCk8TJwQIhANwz\n" + 
			"OfioFPqJtfRLhAY+5bTtS4V8OOIzKCAV8GCTSaaY";

    public static final String TOKEN_HEADER = "Authentication";

    public static String create(String subject) {
    	
//    	Date date = new Date();
//        long t = date.getTime();
//    	Date expirationTime = new Date(t + 55000l); // set 55 seconds
//    	Date expirationTime = new Date(t + 1800000l); // set 30 minutos
    	
        return Jwts.builder()
                .setSubject(subject)
//                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static Jws<Claims> decode(String token) throws Exception {
    	try {
    		return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			throw new JwtExpiredException();
		} catch (Exception e) {
			throw new Exception("");
		}
    }
}
