package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.NamespaceManager;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "SetImagemPerfil",
	    urlPatterns = {"/api/setImagem"}
	)
public class SetImagemPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public SetImagemPerfil() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			  
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");
		    
		    String url = sistema.getURLUploadImagem("/api/setImagem");
		    
		    if(url != null) {
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	response.getWriter().write(
		    			 "{\"status\": \"ok\","
		    			+ "\"url\": \""+ url +"\"}"
		    			);
		    }
		    else {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    }
		    
		}
		catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			response.getWriter().print(sStackTrace);
		}
	    
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		NamespaceManager.set("Podcre");
		
		  try {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");

	    	if(this.getCoockie(request, "nome") == null) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\", "
		    								+ "\"mensagem\": \"Nome não informado\"}");
		    	return;
	    	}
	    	
	    	String id = sistema.uploadImagem(request);
		    
		    if(id != null && this.sistema.setImagem(this.getCoockie(request, "nome"), id)) {
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	response.getWriter().write("{\"status\": \"ok\"}");
		    }
		    else {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    }
		}
		catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			response.getWriter().print(sStackTrace);
		}
	    
	}
	
	private String getCoockie(HttpServletRequest request, String nome) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
		 for (Cookie cookie : cookies)
		   if (cookie.getName().equals(nome))
		     return cookie.getValue();

		
		return null;
	}
	
}
