package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.appengine.api.NamespaceManager;

import Sistema.Sistema;
import Sistema.SistemaInterface;

@WebServlet(
	    name = "ListarNomes",
	    urlPatterns = {"/api/Estatisticas/listaNomes"}
	)
public class EstatisticaListaNomes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SistemaInterface sistema;
	
	public EstatisticaListaNomes() {
		this.sistema = new Sistema();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException {
		  
		NamespaceManager.set("Podcre");
		  
		try {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.addHeader("Access-Control-Allow-Origin", "*");
	
		    Vector<String> lista = sistema.listarNomes();
		    
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
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			response.getWriter().print(sStackTrace);
		}
	    
	}
}
