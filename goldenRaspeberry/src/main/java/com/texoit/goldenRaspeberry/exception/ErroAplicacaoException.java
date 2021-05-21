package com.texoit.goldenRaspeberry.exception;

/**
 * ErroAplicacaoException
 * @author Vinicius Alvarenga
 *
 */
public class ErroAplicacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroAplicacaoException(String message) {
		super(message);
	}

	public ErroAplicacaoException(Throwable t) {
		super(t);
	}

}
