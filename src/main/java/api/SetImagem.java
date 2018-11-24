package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\"}");
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
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\"}");
		}
	    
	}
	
}
