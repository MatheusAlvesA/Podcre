package Sistema;

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
	public Boolean setImagem(String blob);
	
	//TODO
	
}
