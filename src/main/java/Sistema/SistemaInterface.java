package Sistema;

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
	public Boolean setImagem(String blob);
	
	//TODO
	
}
