package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "AtualizarCache",
	    urlPatterns = {"/api/updateCache"}
	)
public class UpdateCache extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public UpdateCache() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
			Boolean r = this.sistema.atualizarCache();
			if(r) {
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	response.getWriter().write("{\"status\": \"ok\"}");
			}
			else {
		    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
