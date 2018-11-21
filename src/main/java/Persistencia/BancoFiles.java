package Persistencia;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import Persistencia.PersistenciaException.TipoErro;

public class BancoFiles implements BancoFilesInterface {

	@Override
	public String receberArquivo(HttpServletRequest request) throws PersistenciaException {
		try {
			
		    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		    Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
		    List<BlobKey> blobKeys = blobs.get("arquivo");
	
		    if (blobKeys == null || blobKeys.isEmpty())
		    	return null;
		    else
		    	return blobKeys.get(0).getKeyString();
		    
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
	    
	}

	@Override
	public void enviarArquivo(HttpServletResponse response, String chave) throws PersistenciaException {
		try {
			BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		    BlobKey blobKey = new BlobKey(chave);
		    blobstoreService.serve(blobKey, response);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
	}
	
	@Override
	public String gerarURL(String endpoint) throws PersistenciaException {
		try {
			BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			return blobstoreService.createUploadUrl(endpoint);
		}
		catch (Exception e) {
			PersistenciaException Nova = new PersistenciaException(e);
			Nova.setCodError(TipoErro.FALHA_AO_ACESSAR);
			throw Nova;
		}
	}

}
