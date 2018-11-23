package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		NamespaceManager.set("Podcre");
		
		  try {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");
		    
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
	    	
	    	if(!this.checarChaves(corpoJSON)) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
	    	}
		    
		    if(this.sistema.setImagem(corpoJSON.getString("nome"), corpoJSON.getString("cod"))) {
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
	
	private Boolean checarChaves(JSONObject o) {
		if(o.isNull("nome")) return false;
		if(o.isNull("cod")) return false;
		
		return true;
	}
	
}
