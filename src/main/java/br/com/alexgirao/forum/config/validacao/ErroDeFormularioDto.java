package br.com.alexgirao.forum.config.validacao;

import lombok.Getter;


@Getter
public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
	
}
