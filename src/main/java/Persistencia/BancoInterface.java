package Persistencia;

import java.util.Map;

/*
 * Esta interface define a forma como o sistema interage com o banco de dados.
 * Abstrai a persistencia de dados no banco
 * */

public interface BancoInterface {

	/*
	 * Esta função insere um novo usuário no banco de dados
	 * 
	 * Lança PersistenciaException em caso de nome_user duplicado
	 * ou falha ao acessar o banco
	 * 
	 * nome_user, o nome de usuário (deve ser único)
	 * nome_display, o nome que será mostrado aos visitantes desse usuário
	 * email, o email do usuário(deve ser único)
	 * senha, a senha do usuário (Não será criptografada antes de ser inserida no banco)
	 * url_imagem, A url da localização da imagem deste usuário
	 * */
	public void insertUser(String nome_user, String nome_display, String email, String senha, String url_imagem) throws PersistenciaException;
	
	/*
	 * Retorna um usuário dado seu nome de usuário único
	 * ou null caso o usuário não exista
	 * 
	 * Pode lançar PersistenciaException caso ocorra uma falha na comunicação com o banco de dados
	 * */
	public Map<String, String> getUser(String nome_user) throws PersistenciaException;
	
	/*
	 * Insere um podcast no banco associando-o com um usuário
	 * 
	 * Pode lançar PersistenciaException em caso de usuário inexistente
	 * ou falha no banco de dados
	 * 
	 * nome_user, o nome de usuário (Deve existir previamente)
	 * n_listeners, o numero de vezes que o audio foi escutado
	 * n_likes, curtidas
	 * n_dislikes, DEScurtidas
	 * url, a URL para o arquivo de audio .mp3
	 * assunto, O assunto tratado no audio em questão
	 * */
	public void insertPodcast(String nome_user, String n_listeners, int n_likes, int n_dislikes, String url, String assunto) throws PersistenciaException;
	
	/*
	 * Retorna um vetor com todos os audios upados por este usuário
	 * 
	 * pode lançar PersistenciaException em caso de falha no banco
	 * 
	 * nome_user, o nome do usuário do qual se quer obter os audios
	 * */
	public Map<String, String>[] getPodcast(String nome_user) throws PersistenciaException;
	
}
