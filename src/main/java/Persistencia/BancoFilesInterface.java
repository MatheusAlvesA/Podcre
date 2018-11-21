package Persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Esta classe gera abstrai o armazenamento de arquivos no cloud
 * gerando urls para upload e links para downlaod
 * */
public interface BancoFilesInterface {

	/*
	 * Essa função recebe um arquivo enviado via http request e retorna sua key no blob storage
	 * 
	 * pode lançar PersistenciaException em caso de falha na cloud
	 * */
	public String receberArquivo(HttpServletRequest request) throws PersistenciaException;
	
	/*
	 * Essa função envia um arquivo proveniente do blobstorage dado uma chave previamente armazenada
	 * 
	 * pode lançar PersistenciaException em caso de falha na cloud
	 * */
	public void enviarArquivo(HttpServletResponse response, String chave) throws PersistenciaException;
	
	/*
	 * Essa função gera uma url pronta para receber arquivos
	 * 
	 * pode lançar PersistenciaException em caso de falha na cloud
	 * */
	public String gerarURL(String endpoint) throws PersistenciaException;
	
	/*
	 * Esta função deleta um arquivo previamente armazenado na cloud
	 * */
	public Boolean delete(String chave) throws PersistenciaException;
}
