package api;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    
	    if(this.sistema.inserirUsuario(request.getParameter("nome"), request.getParameter("display"), request.getParameter("email"), request.getParameter("senha")))
	    	response.getWriter().write("{\"status\": \"ok\"}");
	    else
	    	response.getWriter().write("{\"status\": \"erro\"}");
	    
	  }
}
