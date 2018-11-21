import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Persistencia.Banco;
import Persistencia.PersistenciaException;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");

    response.getWriter().print("Hello App Engine!\r\n");
    
	Banco b = new Banco();
	try {
		b.insertUser("x", "x", "x", "x", "x");
		response.getWriter().print("INSERIDO");
	} catch (PersistenciaException e) {
		// TODO Auto-generated catch block
		response.getWriter().print("ERRO");
		e.printStackTrace();
	}

  }
}