package Persistencia;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.appengine.api.NamespaceManager;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.FullEntity.Builder;

public class Logger {

	public Boolean logar(Exception e) {
		NamespaceManager.set("Podcre");
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("logErros");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - H:m:s");
			String formattedString = date.format(formatter);
			
			entityBuilder.set("momento", formattedString);
			entityBuilder.set("erro", sStackTrace);
			
			FullEntity<IncompleteKey> entity = entityBuilder.build();
			datastore.put(entity);
			
		}
		catch (Exception ex) {return false;}
		
		return true;

	}
	
}
