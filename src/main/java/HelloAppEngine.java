import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Persistencia.Banco;
import Persistencia.Cache;
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
	Cache c = new Cache();
	
	try {
		
		b.delete("5631986051842048");
		
		/*
		c.set("teste", b.listarNomes(), 60*2);
		Vector<String> vector = (Vector<String>)c.get("teste");
		response.getWriter().print(vector.get(0));
		*/
	} catch (Exception e) {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		response.getWriter().print("ERRO: "+sStackTrace);
		
	}

  }
}