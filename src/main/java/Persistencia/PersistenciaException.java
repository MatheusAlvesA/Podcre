package Persistencia;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 1L;

	public enum TipoErro  {
	    DUPLICADO, FALHA_AO_ACESSAR, NOT_FOUND;
	}
	
	private TipoErro codError;

	public PersistenciaException(TipoErro codError) {
		super();
		this.codError = codError;
	}

	public PersistenciaException(Throwable t) {super(t);}
	
	public TipoErro getCodError() {return this.codError;}
	public void setCodError(TipoErro erro) {this.codError = erro;}
}
