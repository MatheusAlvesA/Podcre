package api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class Autenticador {

	public static String endPoint = "https://orchestrator-224010.appspot.com/token/validate";
	
	public static boolean autenticar(HttpServletRequest r) {
		
		if(r.getHeader("token-id") == null)
			return false;
		
		if(r.getHeader("token-id") == "frontend_podcre")
			return true;
		
		if(r.getHeader("client-appspot") == null)
			return false;
		
		try {
			
			String requestBody = "{" + 
					"	\"service-name\": \"Get info user\",\r\n" + 
					"	\"client-appspot\": \""+ r.getHeader("client-appspot") +"\",\r\n" + 
					"	\"server-appspot\": \"podcre-223420\",\r\n" + 
					"	\"token-id\": \"" + r.getHeader("token-id") + "\"" + 
					"}";
			
			return sendPOST(Autenticador.endPoint, requestBody);
			
		} catch (IOException e) {
			return false;
		}		
	}
	
	private static boolean sendPOST(String url, String corpo) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(corpo.getBytes());
		os.flush();
		os.close();

		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) //http success
			return true;
		else // Http error
			return false;
	}
	
}
