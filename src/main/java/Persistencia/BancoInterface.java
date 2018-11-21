package Persistencia;

import java.util.Map;

/*
 * Esta interface define a forma como o sistema interage com o banco de dados.
 * Abstrai a persistencia de dados no banco
 * */

public interface BancoInterface {

	/*
	 * Esta fun��o insere um novo usu�rio no banco de dados
	 * 
	 * Lan�a PersistenciaException em caso de nome_user duplicado
	 * ou falha ao acessar o banco
	 * 
	 * nome_user, o nome de usu�rio (deve ser �nico)
	 * nome_display, o nome que ser� mostrado aos visitantes desse usu�rio
	 * email, o email do usu�rio(deve ser �nico)
	 * senha, a senha do usu�rio (N�o ser� criptografada antes de ser inserida no banco)
	 * url_imagem, A url da localiza��o da imagem deste usu�rio
	 * */
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem) throws PersistenciaException;
	
	/*
	 * Retorna um usu�rio dado seu nome de usu�rio �nico
	 * ou null caso o usu�rio n�o exista
	 * 
	 * Pode lan�ar PersistenciaException caso ocorra uma falha na comunica��o com o banco de dados
	 * */
	public Map<String, String> getUser(String nome_user) throws PersistenciaException;
	
	/*
	 * Insere um podcast no banco associando-o com um usu�rio
	 * 
	 * Pode lan�ar PersistenciaException em caso de usu�rio inexistente
	 * ou falha no banco de dados
	 * 
	 * nome_user, o nome de usu�rio (Deve existir previamente)
	 * n_listeners, o numero de vezes que o audio foi escutado
	 * n_likes, curtidas
	 * n_dislikes, DEScurtidas
	 * url, a URL para o arquivo de audio .mp3
	 * assunto, O assunto tratado no audio em quest�o
	 * */
	public void insertPodcast(String nome_user, String n_listeners, int n_likes, int n_dislikes, String url, String assunto) throws PersistenciaException;
	
	/*
	 * Retorna um vetor com todos os audios upados por este usu�rio
	 * 
	 * pode lan�ar PersistenciaException em caso de falha no banco
	 * 
	 * nome_user, o nome do usu�rio do qual se quer obter os audios
	 * */
	public Map<String, String>[] getPodcast(String nome_user) throws PersistenciaException;
	
}
