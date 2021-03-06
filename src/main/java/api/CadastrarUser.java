package api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "CadastroUser",
	    urlPatterns = {"/api/user"}
	)
public class CadastrarUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SistemaInterface sistema;
	
	public CadastrarUser() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
		    String corpo = IOUtils.toString(request.getReader());
		    
	    	JSONObject corpoJSON = null;
	    	try {
	    		corpoJSON = new JSONObject(corpo);
	    	}
	    	catch(JSONException e) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
	    	}
		    
		    if(this.sistema.inserirUsuario(corpoJSON.getString("nome"), corpoJSON.getString("display"), corpoJSON.getString("email"), corpoJSON.getString("senha"))) {
		    	
		    	HttpSession s = request.getSession(true);
		    	s.setAttribute("nome", corpoJSON.getString("nome"));
		    	
		    	response.setStatus(HttpServletResponse.SC_CREATED);
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
	    	response.getWriter().write("{\"status\": \"erro\"}");
		}
	    
	}
	  
	  
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		  
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	
			if(!Autenticador.autenticar(request, "Get info user")) {
		    	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"Acesso Negado\"}");
		    	return;
			}
			
		    Map<String, String> user = sistema.getUser(request.getParameter("nome"));
		    
		    if(user == null) {
		    	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
		    }
		    
		    user.remove("senha"); // A senha n�o pode ser enviada
		    
		    JSONObject corpoJSON = new JSONObject(user);
		    
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().write("{\"status\": \"ok\", \"data\": " + corpoJSON.toString() + "}");
	    	
		}
		catch (Exception e) {
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\"}");
		}
	    
	}
}
