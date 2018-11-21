package Persistencia;

/*
 * Esta classe abstrai as funções de cache do módulo de persistencia
 * */
public interface CacheInterface {

	/*
	 * Esta função insere um objeto no cache e o mantem vivo durante 'expira' segundos
	 * 
	 * Pode lançar PersistenciaException em caso de problema com a cloud
	 * 
	 * nome, o nome identificador do objeto(deve ser único para cada objeto)
	 * valor, o objeto java a ser guardado em cache
	 * expira, o número de segundos que o objeto permanecerá salvo
	 * */
	public void set(String nome, Object valor, int expira) throws PersistenciaException;

	/*
	 * Esta função retorna um objeto em cache ou null caso o objeto não exista
	 * 
	 * Pode lançar PersistenciaException em caso de problema com a cloud
	 * 
	 * nome, o nome/identificador único do objeto
	 * */
	public Object get(String nome) throws PersistenciaException;
	
	/*
	 * Esta função apaga completamente o memory cache
	 * */
	public void flush();
}
