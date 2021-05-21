package com.texoit.goldenRaspeberry.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ErroAplicacaoHandler
 * @author Vinicius Alvarenga
 *
 */
@RestControllerAdvice
public class ErroAplicacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ErroAplicacaoException.class)
	public List<ErroAplicacaoDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroAplicacaoDTO> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroAplicacaoDTO erro = new ErroAplicacaoDTO(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}

}
