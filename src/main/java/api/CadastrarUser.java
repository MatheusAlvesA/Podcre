package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "CadastroUser",
	    urlPatterns = {"/api/cadastrarUser"}
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

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    
	    String corpo = IOUtils.toString(request.getReader());
	    
    	JSONObject corpoJSON = null;
    	try {
    		corpoJSON = new JSONObject(corpo);
    	}
    	catch(JSONException e) {
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    	response.getWriter().write("{\"status\": \"erro\"}");
    	}
	    
	    if(this.sistema.inserirUsuario(corpoJSON.getString("nome"), corpoJSON.getString("display"), corpoJSON.getString("email"), corpoJSON.getString("senha"))) {
	    	response.setStatus(HttpServletResponse.SC_CREATED);
	    	response.getWriter().write("{\"status\": \"ok\"}");
	    }
	    else {
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    	response.getWriter().write("{\"status\": \"erro\"}");
	    }
	    
	  }
}
