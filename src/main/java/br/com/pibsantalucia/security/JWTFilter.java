package br.com.pibsantalucia.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.pibsantalucia.util.JwtExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JWTFilter implements Filter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;

	    System.out.print(req.getRequestURI());
	    String path = "";
	    if (System.getenv("DSV") != null) {
	    	path = "/PIBSantaLucia";
	    }
	    
	    if(req.getRequestURI().startsWith(path+"/Api/LoginRest/Login") || 
	    		req.getRequestURI().startsWith(path+"/Api/LoginRest/Cadastro") ||
	    		req.getRequestURI().startsWith(path+"/Api/LoginRest/TrocaSenha") ||
	    		req.getRequestURI().startsWith(path+"/Api/LoginRest/EsquiciSenha") ||
	    		req.getRequestURI().startsWith(path+"/Api/BackupRest/Backup") ||
//	    		req.getRequestURI().startsWith(path+"/Api/RestoreBackupRest/RestoreBackup") ||
	    		req.getRequestURI().startsWith(path+"/Api/EscalaRest/ProximasEscalas") ||
	    		req.getRequestURI().startsWith(path+"/Api/AniversarianteRest/SendPush")) {
	    	
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String token = req.getHeader(JWTUtil.TOKEN_HEADER);

	    if(token == null || token.trim().isEmpty()){
	        res.setStatus(401);
	        return;
	    }

	    try {
	        Jws<Claims> parser = JWTUtil.decode(token);
	        System.out.println("User request: "+ parser.getBody().getSubject());
	        filterChain.doFilter(request, response);
	    } catch (JwtExpiredException e) {
	    	LOGGER.error("************** Token expirado. **************");
	        res.setStatus(425);
	    } catch (Exception e) {
	        res.setStatus(401);
	    }
	}

	public void destroy() {}
}
