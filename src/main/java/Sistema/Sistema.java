package Sistema;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Persistencia.Banco;
import Persistencia.BancoFiles;
import Persistencia.BancoFilesInterface;
import Persistencia.BancoInterface;
import Persistencia.Cache;
import Persistencia.CacheInterface;
import Persistencia.Logger;
import Persistencia.PersistenciaException;

public class Sistema implements SistemaInterface {

	private BancoInterface banco;
	private CacheInterface cache;
	private BancoFilesInterface bancoFiles;
	private Logger logger;
	
	public Sistema() {
		this.banco = new Banco();
		this.cache = new Cache();
		this.bancoFiles = new BancoFiles();
		this.logger = new Logger();
	}
	
	@Override
	public Boolean inserirUsuario(String nome_user, String nome_display, String email, String senha) {
		try {
			this.banco.insertUser(nome_user, nome_display, email, this.encriptar(senha), "");
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean setImagem(String nome_user, String blob) {
		try {
			Map<String, String> user = this.banco.getUser(nome_user);
			if(!user.get("imagem_blob").equals(""))
				this.delete(user.get("imagem_blob"));
			
			this.banco.setImagem(nome_user, blob);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public Vector<Map<String, String>> getPodcasts(String nome_user) {
		Vector<Map<String, String>> retorno = new Vector<Map<String, String>>();
		
		try {
			retorno = this.banco.getPodcast(nome_user);
		}
		catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
		
		return retorno;
	}

	@Override
	public Map<String, String> getUser(String nome_user) {
		Map<String, String> retorno = null;
		
		try {
			retorno = this.banco.getUser(nome_user);
		}
		catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
		
		return retorno;
	}

	@Override
	public Vector<String> listarNomes() {
		try {
			@SuppressWarnings("unchecked")
			Vector<String> r = (Vector<String>) this.cache.get("listaUsers");
			if(r == null)
				return this.banco.listarNomes();
			else
				return r;
		} catch (PersistenciaException e) {
			return this.banco.listarNomes();
		}
	}

	@Override
	public Boolean computarLike(String id) {
		if(id == null) return false;
		try {
			this.banco.like(id);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean computarDisLike(String id) {
		if(id == null) return false;
		try {
			this.banco.disLike(id);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean computarListened(String id) {
		if(id == null) return false;
		try {
			this.banco.listened(id);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean insertPodcast(String nome_user, String nome, String key_blob, String assunto) {
		try {
			this.banco.insertPodcast(nome_user, nome, 0, 0, 0, key_blob, assunto);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}

	@Override
	public String getURLUploadPodcast(String uri) {
		try {
			return this.bancoFiles.gerarURL(uri);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
	}

	@Override
	public String getURLUploadImagem(String uri) {
		try {
			return this.bancoFiles.gerarURL(uri);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
	}

	@Override
	public String uploadPodcast(HttpServletRequest request) {
		try {
			return this.bancoFiles.receberArquivo(request);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
	}

	@Override
	public String uploadImagem(HttpServletRequest request) {
		try {
			return this.bancoFiles.receberArquivo(request);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return null;
		}
	}

	@Override
	public Boolean download(HttpServletResponse response, String chave) {
		try {
			this.bancoFiles.enviarArquivo(response, chave);
		} catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean delete(String chave) {
		try {
			return this.bancoFiles.delete(chave);
		}
		catch (PersistenciaException e) {
			this.logger.logar(e);
			return false;
		}
	}
	
	@Override
	public Boolean logarErro(Exception e) {return this.logger.logar(e);}
	
	public String encriptar(String original) {return org.apache.commons.codec.digest.DigestUtils.sha256Hex(original);}

}
