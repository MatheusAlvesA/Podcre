package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "ContagemLike",
	    urlPatterns = {"/api/contar"}
	)
public class Contagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public Contagem() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			if(!Autenticador.autenticar(request, "Cantabilize like/dislike")) {
		    	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"Acesso Negado\"}");
		    	return;
			}
			
			Boolean r = false;
			
		    if(request.getParameter("qual") == null) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
		    }
		    
			switch (request.getParameter("qual")) {
				case "like":
					r = sistema.computarLike(request.getParameter("cod"));
					break;
				case "dislike":
					r = sistema.computarDisLike(request.getParameter("cod"));
					break;
				case "view":
					r = sistema.computarListened(request.getParameter("cod"));
					break;
				default:
					break;
			}
			
		    if(r) {
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	response.getWriter().write("{\"status\": \"ok\"}");
		    }
		    else {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    }
		    
		}
		catch (Exception e) {
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"" + e.getMessage() + "\"}");
		}
	    
	}

}
