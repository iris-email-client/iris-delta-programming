package br.unb.cic.iris.core.exception;

/**
 *  Classe usada para representar erros que podem ocorrer na 
 *  camada de persistencia. 
 *
 * @author rbonifacio
 */
public class DBException extends EmailException {

	private static final long serialVersionUID = 1L;

	public DBException(String message, Exception cause) {
		super(message, cause);
	}
}
