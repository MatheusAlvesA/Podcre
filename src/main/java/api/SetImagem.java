package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.NamespaceManager;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "SetImagemDePerfil",
	    urlPatterns = {"/api/setImagemPerfil"}
	)
public class SetImagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public SetImagem() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
		    String url = sistema.getURLUploadImagem("/api/setImagemPerfil");
		    
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

		    
		    if(id != null && this.sistema.setImagem((String)s.getAttribute("nome"), id)) {
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
	
}
