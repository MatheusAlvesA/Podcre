package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Sistema.Sistema;
import Sistema.SistemaInterface;


@WebServlet(
	    name = "SetImagemPerfil",
	    urlPatterns = {"/api/getFile"}
	)
public class GetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SistemaInterface sistema;
	
	public GetFile() {
		this.sistema = new Sistema();
	}
	
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if(!this.sistema.download(response, request.getParameter("cod")))
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    
	  }
}