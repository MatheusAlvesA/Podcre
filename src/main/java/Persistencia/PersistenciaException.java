package Persistencia;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 1L;

	public enum TipoErro  {
	    DUPLICADO, FALHA_AO_ACESSAR;
	}
	
	private TipoErro codError;

	public PersistenciaException(TipoErro codError) {
		super();
		this.codError = codError;
	}
	
	public TipoErro getCodError() {return this.codError;}
}
