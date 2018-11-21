package Persistencia;

import java.util.Map;

public class Banco implements BancoInterface {

	@Override
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem)
			throws PersistenciaException {
		// TODO Auto-generated method stub
		
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
