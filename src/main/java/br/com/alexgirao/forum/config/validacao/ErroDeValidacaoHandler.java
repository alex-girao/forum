package br.com.alexgirao.forum.config.validacao;

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
 * Classe que intercepta os erros de validacao 
 * dos controllers
 * 
 * @author alexgirao
 */
@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	//auxilia na captura de mensagens de erro
	@Autowired
	private MessageSource messageSource;
	
	// Evita o retorno 200
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	// observa as excecoes de erro de validacao
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			// pega a mensagem, no idioma correto
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});
		return dto;
	}

}
