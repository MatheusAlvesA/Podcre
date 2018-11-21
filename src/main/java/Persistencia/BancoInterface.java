package Persistencia;

import java.util.Map;
import java.util.Vector;

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
	 * imagem_blob, A key da localização da imagem deste usuário
	 * */
	public void insertUser(String nome_user, String nome_display, String email, String senha, String imagem_blob) throws PersistenciaException;
	
	/*
	 * Seta a imagem de perfil de um usuário previamente inserido no banco
	 * */
	public void setImagem(String nome_user, String blob) throws PersistenciaException;
	
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
	 * nome, O nome do episódio de podcast sendo inserido
	 * n_listeners, o numero de vezes que o audio foi escutado
	 * n_likes, curtidas
	 * n_dislikes, DEScurtidas
	 * key_blob, a chave para o arquivo de audio .mp3 no Blobstore
	 * assunto, O assunto tratado no audio em questão
	 * */
	public void insertPodcast(String nome_user, String nome, int n_listeners, int n_likes, int n_dislikes, String key_blob, String assunto) throws PersistenciaException;
	
	/*
	 * Retorna um vetor com todos os audios upados por este usuário
	 * 
	 * pode lançar PersistenciaException em caso de falha no banco
	 * 
	 * nome_user, o nome do usuário do qual se quer obter os audios
	 * */
	public Vector< Map<String, Object> > getPodcast(String nome_user) throws PersistenciaException;
	
	/*
	 * Esta função adiciona um like ao podcast cujo id foi passado
	 * 
	 * Pode lançar PersistenciaException em caso de não encontrar o podcast
	 * ou em caso de falha ao acessar o banco de dados
	 * */
	public void like(String id) throws PersistenciaException;
	
	/*
	 * Esta função adiciona um dislike ao podcast cujo id foi passado
	 * 
	 * Pode lançar PersistenciaException em caso de não encontrar o podcast
	 * ou em caso de falha ao acessar o banco de dados
	 * */
	public void disLike(String id) throws PersistenciaException;
	
	/*
	 * Esta função adiciona um listener ao podcast cujo id foi passado
	 * 
	 * Pode lançar PersistenciaException em caso de não encontrar o podcast
	 * ou em caso de falha ao acessar o banco de dados
	 * */
	public void listened(String id) throws PersistenciaException;
	
	/*
	 * Esta função deleta um podcast dado o seu ID
	 * 
	 * Pode lançar PersistenciaException em caso de não encontrar o podcast
	 * ou em caso de falha ao acessar o banco de dados
	 * */
	public void delete(String id) throws PersistenciaException;
	
	/*
	 * Retorna uma lista de todos os nomes de usuários
	 * */
	public Vector<String> listarNomes();
}
