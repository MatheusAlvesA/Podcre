package Sistema;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Esta � a interface principal do sistema, 
 * */
public interface SistemaInterface {

	/*
	 * Esta fun��o insere um usu�rio no banco 
	 * Retorna true em caso de sucesso e false
	 * em caso de falha por qualquer motivo
	 * 
	 * nome_user, o nome de usu�rio, deve ser unico caso constr�rio ser� retornado false
	 * nome_display, o nome que ser� mostrado aos visitantes desse usu�rio
	 * email, o email do usu�rio
	 * senha, a senha do usu�rio (Ser� criptografada com SHA256)
	 * */
	public Boolean inserirUsuario(String nome_user, String nome_display, String email, String senha);
	
	/*
	 * Associa a chave de uma imagem upada para o blob a um usuario
	 * */
	public Boolean setImagem(String nome_user, String blob);
	
	/*
	 * Retorna um vetor com todos os audios upados por este usu�rio
	 * 
	 * nome_user, o nome do usu�rio do qual se quer obter os audios
	 * */
	public Vector< Map<String, Object> > getPodcasts(String nome_user);
	
	/*
	 * Retorna um usu�rio dado seu nome de usu�rio �nico
	 * ou null caso o usu�rio n�o exista
	 * */
	public Map<String, String> getUser(String nome_user);
	
	/*
	 * Retorna uma lista de todos os nomes de usu�rios
	 * */
	public Vector<String> listarNomes();
	
	public Boolean computarLike(String id);
	public Boolean computarDisLike(String id);
	public Boolean computarListened(String id);
	
	/*
	 * Insere um podcast no banco associando-o com um usu�rio
	 * 
	 * Pode lan�ar PersistenciaException em caso de usu�rio inexistente
	 * ou falha no banco de dados
	 * 
	 * nome_user, o nome de usu�rio (Deve existir previamente)
	 * nome, O nome do epis�dio de podcast sendo inserido
	 * key_blob, a key blob para o arquivo de audio .mp3
	 * assunto, O assunto tratado no audio em quest�o
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
	 * uri,  a uri onde ser� feio o upload
	 * */
	public String getURLUploadImagem(String uri);
	
	/*
	 * Esta fun��o guarda na cloud um podcast upado na requisi��o
	 * */
	public String uploadPodcast(HttpServletRequest request);
	
	/*
	 * Esta fun��o guarda na cloud uma imagem de perfil upada na requisi��o
	 * */
	public String uploadImagem(HttpServletRequest request);
	
	/*
	 * Executa o download de um arquivo direto da cloud
	 * */
	public Boolean download(HttpServletResponse response, String chave);
}
