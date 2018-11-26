package api;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "GetPodcasts",
	    urlPatterns = {"/api/getPodcasts"}
	)
public class GetPodcasts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SistemaInterface sistema;
	
	public GetPodcasts() {
		this.sistema = new Sistema();
	}
	  
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		  
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	
		    Vector<Map<String, String>> lista = sistema.getPodcasts(request.getParameter("nome"));
		    
		    if(lista == null) {
		    	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
		    }
		    
		    JSONArray corpoJSON = new JSONArray();
		    for(Map<String, String> epi: lista) {
		    	corpoJSON.put(new JSONObject(epi));
		    }

	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().write("{\"status\": \"ok\", \"data\": " + corpoJSON.toString() + "}");
	    	
		}
		catch (Exception e) {
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"" + e.getMessage() + "\"}");
		}
	    
	}
}
