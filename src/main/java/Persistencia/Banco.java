package Persistencia;

import java.util.Map;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import Persistencia.PersistenciaException.TipoErro;

public class Banco implements BancoInterface {

	public Banco() {NamespaceManager.set("Podcre");}
	
	@Override
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem)
			throws PersistenciaException {

		DatastoreService datastore = null;
		Transaction txn = null;

		try {
			datastore = DatastoreServiceFactory.getDatastoreService();
			txn = (Transaction) datastore.beginTransaction();
		}
		catch (Exception e) {throw new PersistenciaException(TipoErro.FALHA_AO_ACESSAR);}
		
		try {
			
			EntityQuery query = Query.newEntityQueryBuilder()
				    .setKind("Task")
				    .setFilter(CompositeFilter.and(
				        PropertyFilter.eq("done", false), PropertyFilter.ge("priority", 4)))
				    .setOrderBy(OrderBy.desc("priority"))
				    .build();
			
			Entity novo = new Entity("Usuarios", nome_user);

			novo.setProperty("nome_user", nome_user);
	
			datastore.put(novo);
			txn.commit();
		}
		catch (Exception e) {
			throw new PersistenciaException(TipoErro.FALHA_AO_ACESSAR);
		}
		finally {
			if (txn.isActive()) { // Não foi comitada
				txn.rollback();
			}
		}
	}

	@Override
	public Map<String, String> getUser(String nome_user) throws PersistenciaException {
		// TODO Auto-generated method stub
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
