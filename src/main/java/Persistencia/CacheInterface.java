package Persistencia;

/*
 * Esta classe abstrai as fun��es de cache do m�dulo de persistencia
 * */
public interface CacheInterface {

	/*
	 * Esta fun��o insere um objeto no cache e o mantem vivo durante 'expira' segundos
	 * 
	 * Pode lan�ar PersistenciaException em caso de problema com a cloud
	 * 
	 * nome, o nome identificador do objeto(deve ser �nico para cada objeto)
	 * valor, o objeto java a ser guardado em cache
	 * expira, o n�mero de segundos que o objeto permanecer� salvo
	 * */
	public void set(String nome, Object valor, int expira) throws PersistenciaException;

	/*
	 * Esta fun��o retorna um objeto em cache ou null caso o objeto n�o exista
	 * 
	 * Pode lan�ar PersistenciaException em caso de problema com a cloud
	 * 
	 * nome, o nome/identificador �nico do objeto
	 * */
	public Object get(String nome) throws PersistenciaException;
	
	/*
	 * Esta fun��o apaga completamente o memory cache
	 * */
	public void flush();
}
