package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

@WebServlet(
	    name = "Login",
	    urlPatterns = {"/api/Login"}
	)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Sistema sistema;
	
	public Login() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
				response.addHeader("Access-Control-Allow-Origin", "*");
		  try {
			  	
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");

			    String corpo = IOUtils.toString(request.getReader());
			    
		    	JSONObject corpoJSON = null;
		    	try {
		    		corpoJSON = new JSONObject(corpo);
		    		corpoJSON.getString("nome");  // Testando se o valor existe
		    		corpoJSON.getString("senha"); // Testando se o valor existe
		    	}
		    	catch(JSONException e) {
			    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"Requisição mal formada\"}");
			    	return;
		    	}
			    
		    	Map<String, String> user = this.sistema.getUser(corpoJSON.getString("nome"));
		    	
			    if( user != null && user.get("senha").equals( this.sistema.encriptar(corpoJSON.getString("senha")) ) ) {
			    	
			    	HttpSession s = request.getSession(true);
			    	s.setAttribute("nome", user.get("nome_user"));
			    	
			    	response.setStatus(HttpServletResponse.SC_OK);
			    	response.getWriter().write("{\"status\": \"ok\"}");
			    }
			    else {
			    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"Usuário ou senha incorretos\"}");
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
	public void doDelete(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
			response.addHeader("Access-Control-Allow-Origin", "*");
		  try {
			  	
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
		    	
		    	HttpSession s = request.getSession(false);
			    
			    if( s != null ) {
			    	s.invalidate();
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
}
