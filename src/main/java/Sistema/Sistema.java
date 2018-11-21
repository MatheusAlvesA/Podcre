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
import Persistencia.PersistenciaException;

public class Sistema implements SistemaInterface {

	private BancoInterface banco;
	private CacheInterface cache;
	private BancoFilesInterface bancoFiles;
	
	public Sistema() {
		this.banco = new Banco();
		this.cache = new Cache();
		this.bancoFiles = new BancoFiles();
	}
	
	@Override
	public Boolean inserirUsuario(String nome_user, String nome_display, String email, String senha) {
		try {
			this.banco.insertUser(nome_user, nome_display, email, senha, "");
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean setImagem(String nome_user, String blob) {
		try {
			this.banco.setImagem(nome_user, blob);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Vector<Map<String, Object>> getPodcasts(String nome_user) {
		Vector<Map<String, Object>> retorno = new Vector<Map<String, Object>>();
		
		try {
			retorno = this.banco.getPodcast(nome_user);
		}
		catch (PersistenciaException e) {
			// TODO: handle exception
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
			// TODO: handle exception
		}
		
		return retorno;
	}

	@Override
	public Vector<String> listarNomes() {
		try {
			Vector<String> r = (Vector<String>) this.cache.get("listaUsers");
			if(r == null)
				return this.banco.listarNomes();
			else
				return r;
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return this.banco.listarNomes();
		}
	}

	@Override
	public Boolean computarLike(String id) {
		try {
			this.banco.like(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean computarDisLike(String id) {
		try {
			this.banco.disLike(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean computarListened(String id) {
		try {
			this.banco.listened(id);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean insertPodcast(String nome_user, String nome, String key_blob, String assunto) {
		try {
			this.banco.insertPodcast(nome_user, nome, 0, 0, 0, key_blob, assunto);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	@Override
	public String getURLUploadPodcast() {
		try {
			return this.bancoFiles.gerarURL("/podcastUp");
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public String getURLUploadImagem() {
		try {
			return this.bancoFiles.gerarURL("/imagemUp");
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public String uploadPodcast(HttpServletRequest request) {
		try {
			return this.bancoFiles.receberArquivo(request);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public String uploadImagem(HttpServletRequest request) {
		try {
			return this.bancoFiles.receberArquivo(request);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Boolean download(HttpServletResponse response, String chave) {
		try {
			this.bancoFiles.enviarArquivo(response, chave);
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

}
