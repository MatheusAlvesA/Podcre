package Persistencia;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.google.appengine.api.NamespaceManager;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.FullEntity.Builder;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import Persistencia.PersistenciaException.TipoErro;

public class Banco implements BancoInterface {

	public Banco() {NamespaceManager.set("Podcre");}
	
	@Override
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem)
			throws PersistenciaException {
		
		try {
			this.getUser(nome_user);
			throw new PersistenciaException(TipoErro.DUPLICADO);
		}
		catch (PersistenciaException ex){/* Neste caso o usuário ainda não existe no banco */}
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("Usuario");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			entityBuilder.set("nome_user", nome_user);
			entityBuilder.set("nome_display", nome_display);
			entityBuilder.set("email", email);
			entityBuilder.set("senha", senha);
			entityBuilder.set("url_imagem", url_imagem);
			
			FullEntity<IncompleteKey> entity = entityBuilder.build();
			datastore.put(entity);
			
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}

	}

	@Override
	public Map<String, String> getUser(String nome_user) throws PersistenciaException {
		
		QueryResults<Entity> tasks = null;
		try {
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			EntityQuery query = Query.newEntityQueryBuilder()
				    .setKind("Usuario")
				    .setFilter( PropertyFilter.eq("nome_user", nome_user) )
				    .build();
			tasks = datastore.run(query);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
		Entity e = null;
		try {e = tasks.next();}
		catch (NoSuchElementException ex) {
			PersistenciaException Nova = new PersistenciaException(ex);
			Nova.setCodError(TipoErro.NOT_FOUND);
			throw Nova;
		}
		
		Map<String, String> retorno = new HashMap<String, String>();
		
		retorno.put("nome_user", e.getString("nome_user"));
		retorno.put("nome_display", e.getString("nome_display"));
		retorno.put("email", e.getString("email"));
		retorno.put("senha", e.getString("senha"));
		retorno.put("url_imagem", e.getString("url_imagem"));
		
		return retorno;
	}

	@Override
	public void insertPodcast(String nome_user, String nome, int n_listeners, int n_likes, int n_dislikes, String url,
			String assunto) throws PersistenciaException {
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("Podcast");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			entityBuilder.set("nome_user", nome_user);
			entityBuilder.set("n_listeners", n_listeners);
			entityBuilder.set("n_likes", n_likes);
			entityBuilder.set("n_dislikes", n_dislikes);
			entityBuilder.set("url", url);
			entityBuilder.set("assunto", assunto);
			entityBuilder.set("nome", nome);
			
			FullEntity<IncompleteKey> entity = entityBuilder.build();
			datastore.put(entity);
			
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}

	@Override
	public Vector< Map<String, Object> > getPodcast(String nome_user) throws PersistenciaException {
		QueryResults<Entity> podcast = null;
		try {
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			EntityQuery query = Query.newEntityQueryBuilder()
				    .setKind("Podcast")
				    .setFilter( PropertyFilter.eq("nome_user", nome_user) )
				    .build();
			podcast = datastore.run(query);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
		Vector< Map<String, Object> > retorno = new Vector< Map<String, Object> >();
		while(podcast.hasNext()) {
			Entity e = podcast.next();
			Map<String, Object> novo = new HashMap<String, Object>();
			
			novo.put("chave", e.getKey()/*.getId()*/);
			novo.put("nome_user", e.getString("nome_user"));
			novo.put("n_listeners",  (int) e.getLong("n_listeners") );
			novo.put("n_likes",  (int) e.getLong("n_likes") );
			novo.put("n_dislikes", (int) e.getLong("n_dislikes") );
			novo.put("url", e.getString("url"));
			novo.put("nome", e.getString("nome"));
			novo.put("assunto", e.getString("assunto"));
			
			retorno.add(novo);
		}
		
		return retorno;
	}
	
	@Override
	public void like(String id) throws PersistenciaException {
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		KeyFactory kf = datastore.newKeyFactory().setKind("Podcast");
		Key chave = kf.newKey(Long.parseLong(id));

		Entity temp = datastore.get(chave);
		
		if(temp == null)
			throw new PersistenciaException(TipoErro.NOT_FOUND);

		try {
			Entity podcast = Entity.newBuilder(temp).set("n_likes", temp.getLong("n_likes")+1).build();
			
			datastore.update(podcast);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}
	
	@Override
	public void disLike(String id) throws PersistenciaException {
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		KeyFactory kf = datastore.newKeyFactory().setKind("Podcast");
		Key chave = kf.newKey(Long.parseLong(id));

		Entity temp = datastore.get(chave);
		
		if(temp == null)
			throw new PersistenciaException(TipoErro.NOT_FOUND);

		try {
			Entity podcast = Entity.newBuilder(temp).set("n_dislikes", temp.getLong("n_dislikes")+1).build();
			
			datastore.update(podcast);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}
	
	@Override
	public void listened(String id) throws PersistenciaException {
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		KeyFactory kf = datastore.newKeyFactory().setKind("Podcast");
		Key chave = kf.newKey(Long.parseLong(id));

		Entity temp = datastore.get(chave);
		
		if(temp == null)
			throw new PersistenciaException(TipoErro.NOT_FOUND);

		try {
			Entity podcast = Entity.newBuilder(temp).set("n_listeners", temp.getLong("n_listeners")+1).build();
			
			datastore.update(podcast);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}
	
	@Override
	public void delete(String id) throws PersistenciaException {
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

			KeyFactory kf = datastore.newKeyFactory().setKind("Podcast");
			Key chave = kf.newKey(Long.parseLong(id));
			
			datastore.delete(chave);
			
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}
	
	public Vector<String> listarNomes() {
		Vector<String> retorno = new Vector<String>();
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		EntityQuery query = Query.newEntityQueryBuilder()
			    .setKind("Podcast")
			    .build();
		QueryResults<Entity> podcast = datastore.run(query);
		
		while(podcast.hasNext()) {
			Entity temp = podcast.next();
			retorno.add(temp.getString("nome_user"));
		}
		
		return retorno;
	}
}
