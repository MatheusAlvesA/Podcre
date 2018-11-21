package Persistencia;

/*
 * Esta interface define a forma como o sistema interage com o banco de dados.
 * Abstrai a persistencia de dados no banco
 * */

public interface BancoInterface {

	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem) throws PersistenciaException;
	
	public void insertPodcast(String nome_user, String n_listeners, int n_likes, int n_dislikes, String url, String assunto) throws PersistenciaException;
	
}
