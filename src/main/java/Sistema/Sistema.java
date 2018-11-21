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
	public Boolean setImagem(String blob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Map<String, Object>> getPodcasts(String nome_user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getUser(String nome_user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<String> listarNomes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean computarLike(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean computarDisLike(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean computarListened(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPodcast(String nome_user, String nome, String key_blob, String assunto) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getURLUploadPodcast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getURLUploadImagem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadPodcast(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadImagem(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean download(HttpServletResponse response, String chave) {
		// TODO Auto-generated method stub
		return null;
	}

}
