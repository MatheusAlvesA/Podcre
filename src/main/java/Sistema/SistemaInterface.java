package Sistema;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Esta é a interface principal do sistema, 
 * */
public interface SistemaInterface {

	/*
	 * Esta função insere um usuário no banco 
	 * Retorna true em caso de sucesso e false
	 * em caso de falha por qualquer motivo
	 * 
	 * nome_user, o nome de usuário, deve ser unico caso constrário será retornado false
	 * nome_display, o nome que será mostrado aos visitantes desse usuário
	 * email, o email do usuário
	 * senha, a senha do usuário (Será criptografada com SHA256)
	 * */
	public Boolean inserirUsuario(String nome_user, String nome_display, String email, String senha);
	
	/*
	 * Associa a chave de uma imagem upada para o blob a um usuario
	 * */
	public Boolean setImagem(String nome_user, String blob);
	
	/*
	 * Retorna um vetor com todos os audios upados por este usuário
	 * 
	 * nome_user, o nome do usuário do qual se quer obter os audios
	 * */
	public Vector< Map<String, Object> > getPodcasts(String nome_user);
	
	/*
	 * Retorna um usuário dado seu nome de usuário único
	 * ou null caso o usuário não exista
	 * */
	public Map<String, String> getUser(String nome_user);
	
	/*
	 * Retorna uma lista de todos os nomes de usuários
	 * */
	public Vector<String> listarNomes();
	
	public Boolean computarLike(String id);
	public Boolean computarDisLike(String id);
	public Boolean computarListened(String id);
	
	/*
	 * Insere um podcast no banco associando-o com um usuário
	 * 
	 * Pode lançar PersistenciaException em caso de usuário inexistente
	 * ou falha no banco de dados
	 * 
	 * nome_user, o nome de usuário (Deve existir previamente)
	 * nome, O nome do episódio de podcast sendo inserido
	 * key_blob, a key blob para o arquivo de audio .mp3
	 * assunto, O assunto tratado no audio em questão
	 * */
	public Boolean insertPodcast(String nome_user, String nome, String key_blob, String assunto);
	
	/*
	 * Retorna uma url para onde o upload de um arquivo deve ser direcionado
	 * retorna null em caso de problema
	 * */
	public String getURLUploadPodcast();
	
	/*
	 * Retorna uma url para onde o upload de um arquivo deve ser direcionado
	 * retorna null em caso de problema
	 * 
	 * uri,  a uri onde será feio o upload
	 * */
	public String getURLUploadImagem(String uri);
	
	/*
	 * Esta função guarda na cloud um podcast upado na requisição
	 * */
	public String uploadPodcast(HttpServletRequest request);
	
	/*
	 * Esta função guarda na cloud uma imagem de perfil upada na requisição
	 * */
	public String uploadImagem(HttpServletRequest request);
	
	/*
	 * Executa o download de um arquivo direto da cloud
	 * */
	public Boolean download(HttpServletResponse response, String chave);
}
