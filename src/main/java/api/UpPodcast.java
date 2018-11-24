package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "UploadPodcast",
	    urlPatterns = {"/api/upPodcast"}
	)
public class UpPodcast extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public UpPodcast() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
		    String url = sistema.getURLUploadPodcast("/api/upPodcast");
		    
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
		
		  try {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

		    HttpSession s = request.getSession(false);
		    String id = sistema.uploadImagem(request);
		    
	    	if(s == null) {
	    		this.sistema.delete(id);
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\", "
		    								+ "\"mensagem\": \"Usuário não logado\"}");
		    	return;
	    	}

		    if(this.getFromCookie(request.getCookies(), "nome") == null || this.getFromCookie(request.getCookies(), "assunto") == null) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\", "
		    								+ "\"mensagem\": \"Os cookies não estão corretos\"}");
		    }
	    	
		    if(id != null &&
		    		this.sistema.insertPodcast(
		    				(String)s.getAttribute("nome"),
		    				this.getFromCookie(request.getCookies(), "nome"),
		    				id,
		    				this.getFromCookie(request.getCookies(), "assunto")
		    		))
		    {
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	response.sendRedirect("/");
		    }
		    else {
		    	this.sistema.delete(id);
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	if(id == null)
			    	response.getWriter().write("{\"status\": \"erro\", "
							+ "\"mensagem\": \"Não foi possível obter o id do blob\"}");
		    	else
			    	response.getWriter().write("{\"status\": \"erro\", "
							+ "\"mensagem\": \""+(String)s.getAttribute("nome")+"|"+id+"\"}");
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
	
	private String getFromCookie(Cookie[] cookies, String nome) {
		if (cookies != null)
		 for (Cookie cookie : cookies)
		   if (cookie.getName().equals(nome))
			   return cookie.getValue();

		return null;
	}
}
