package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "RegistrarGeoloc",
	    urlPatterns = {"/api/Geoloc"}
	)
public class RegistrarGeoloc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public RegistrarGeoloc() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		  try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    
			String corpo = IOUtils.toString(request.getReader());
			Map<String, String> dados = this.extrair(corpo);
			
			if(dados == null) {
		    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
			}
			
			if(!this.sistema.insertGeoloc(dados.get("user"), dados.get("latitude"), dados.get("longitude"))) {
		    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
			}
			
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().write("{\"status\": \"ok\"}");
		    
		}
		catch (Exception e) {
			this.sistema.logarErro(e);
	    	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    	response.getWriter().write("{\"status\": \"erro\", \"mensagem\": \"" + e.getMessage() + "\"}");
		}
	    
	}
	
	public Map<String, String> extrair(String corpo) {
		try {
			JSONObject corpoJSON = new JSONObject(corpo);
			JSONObject corpoArray = (JSONObject) corpoJSON.getJSONArray("data").get(0);
			
			Map<String, String> r = new HashMap<String, String>();
			r.put("user", corpoArray.getString("id"));
			r.put("latitude", corpoArray.getJSONObject("latitude").getString("value"));
			r.put("longitude", corpoArray.getJSONObject("longitude").getString("value"));
			
			return r;
		}
		catch(JSONException e) {return null;}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
	
		    Vector<String> lista = sistema.listarLocs(request.getParameter("nome"));
		    
		    if(lista == null || lista.isEmpty()) {
		    	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    	response.getWriter().write("{\"status\": \"erro\"}");
		    	return;
		    }
		    
		    JSONArray corpoJSON = new JSONArray(lista.toArray());
		    
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
