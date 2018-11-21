package Persistencia;

import java.util.Map;

import com.google.appengine.api.NamespaceManager;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.FullEntity.Builder;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import Persistencia.PersistenciaException.TipoErro;

public class Banco implements BancoInterface {

	public Banco() {NamespaceManager.set("Podcre");}
	
	@Override
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem)
			throws PersistenciaException {
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder();
			entityBuilder.set("Nome", "Valor");
			FullEntity<IncompleteKey> entity = entityBuilder.build();
			datastore.put(entity);
			
		}
		catch (Exception e) {
			throw new PersistenciaException(TipoErro.FALHA_AO_ACESSAR);
		}
	}

	@Override
	public Map<String, String> getUser(String nome_user) throws PersistenciaException {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		EntityQuery query = Query.newEntityQueryBuilder()
			    .setKind("Usuario")
			    .setFilter(CompositeFilter.and(
			        PropertyFilter.eq("user_name", nome_user)))
			    .build();
		QueryResults<Entity> tasks = datastore.run(query);
		
		Entity e = tasks.next();
		
		//TODO
		
		return null;
	}

	@Override
	public void insertPodcast(String nome_user, String n_listeners, int n_likes, int n_dislikes, String url,
			String assunto) throws PersistenciaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String>[] getPodcast(String nome_user) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
