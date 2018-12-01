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
	public void insertUser(String nome_user, String nome_display, String email, String senha, String imagem_blob)
			throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
		Map<String, String> temp = null;
		try {
			temp = this.getUser(nome_user);
		}
		catch (PersistenciaException ex){/* Neste caso o usuário ainda não existe no banco */}
		if(temp != null) // Se o usuário já existe no banco
			throw new PersistenciaException(TipoErro.DUPLICADO);
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("Usuario");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			entityBuilder.set("nome_user", nome_user);
			entityBuilder.set("nome_display", nome_display);
			entityBuilder.set("email", email);
			entityBuilder.set("senha", senha);
			entityBuilder.set("imagem_blob", imagem_blob);
			
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
	public void setImagem(String nome_user, String blob) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

		String chave_user = this.getUser(nome_user).get("chave");
		
		KeyFactory kf = datastore.newKeyFactory().setKind("Usuario");
		Key chave = kf.newKey(Long.parseLong(chave_user));

		Entity temp = datastore.get(chave);
		
		if(temp == null)
			throw new PersistenciaException(TipoErro.NOT_FOUND);

		try {
			Entity u = Entity.newBuilder(temp).set("imagem_blob", blob).build();
			
			datastore.update(u);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}
	
	@Override
	public Map<String, String> getUser(String nome_user) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
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
		retorno.put("imagem_blob", e.getString("imagem_blob"));
		retorno.put("chave", e.getKey().getId().toString());
		
		return retorno;
	}

	@Override
	public void insertPodcast(String nome_user, String nome, int n_listeners, int n_likes, int n_dislikes, String key_blob,
			String assunto) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("Podcast");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			entityBuilder.set("nome_user", nome_user);
			entityBuilder.set("n_listeners", n_listeners);
			entityBuilder.set("n_likes", n_likes);
			entityBuilder.set("n_dislikes", n_dislikes);
			entityBuilder.set("key_blob", key_blob);
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
	public Vector< Map<String, String> > getPodcast(String nome_user) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
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
		
		Vector< Map<String, String> > retorno = new Vector< Map<String, String> >();
		while(podcast.hasNext()) {
			Entity e = podcast.next();
			Map<String, String> novo = new HashMap<String, String>();
			
			novo.put("chave", e.getKey().getId().toString());
			novo.put("nome_user", e.getString("nome_user"));
			novo.put("n_listeners",  String.valueOf(e.getLong("n_listeners")) );
			novo.put("n_likes",  String.valueOf(e.getLong("n_likes")) );
			novo.put("n_dislikes", String.valueOf(e.getLong("n_dislikes")) );
			novo.put("key_blob", e.getString("key_blob"));
			novo.put("nome", e.getString("nome"));
			novo.put("assunto", e.getString("assunto"));
			
			retorno.add(novo);
		}
		
		return retorno;
	}
	
	@Override
	public void like(String id) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
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
		NamespaceManager.set("Podcre");
		
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
		NamespaceManager.set("Podcre");
		
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
		NamespaceManager.set("Podcre");
		
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
	
	@Override
	public void insertGeoloc(String nome_user, String latitude, String longitude)
			throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
		try {
			
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			KeyFactory keyFactory = datastore.newKeyFactory();
			keyFactory.setKind("Geolocalization");
			Builder<IncompleteKey> entityBuilder = Entity.newBuilder(keyFactory.newKey());

			entityBuilder.set("user", nome_user);
			entityBuilder.set("latitude", latitude);
			entityBuilder.set("longitude", longitude);
			
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
	public Vector<String> listarLocs(String nome_user) throws PersistenciaException {
		NamespaceManager.set("Podcre");
		
		Vector<String> retorno = new Vector<String>();
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		EntityQuery query = Query.newEntityQueryBuilder()
			    .setKind("Geolocalization")
			    .setFilter( PropertyFilter.eq("user", nome_user) )
			    .build();
		QueryResults<Entity> lista = datastore.run(query);
		
		while(lista.hasNext()) {
			Entity temp = lista.next();
			retorno.add(temp.getString("latitude")+", "+temp.getString("longitude"));
		}
		
		return retorno;
	}
	
	@Override
	public Vector<String> listarNomes() {
		NamespaceManager.set("Podcre");
		
		Vector<String> retorno = new Vector<String>();
		
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		EntityQuery query = Query.newEntityQueryBuilder()
			    .setKind("Usuario")
			    .build();
		QueryResults<Entity> lista = datastore.run(query);
		
		while(lista.hasNext()) {
			Entity temp = lista.next();
			retorno.add(temp.getString("nome_user"));
		}
		
		return retorno;
	}
}
