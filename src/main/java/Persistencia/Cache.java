package Persistencia;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import Persistencia.PersistenciaException.TipoErro;

public class Cache implements CacheInterface {
	
	private static MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
	
	@Override
	public void set(String nome, Object valor, int expira) throws PersistenciaException {
		try {
			memcache.put(nome, valor, Expiration.byDeltaSeconds(expira));
		}
		catch(Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
		
	}

	@Override
	public Object get(String nome) throws PersistenciaException {
		try {
			return memcache.get(nome);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
	}
	
	@Override
	public void flush() {
		memcache.clearAll();
	}

}
