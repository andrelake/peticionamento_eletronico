package com.andrelake.peticionamento.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PROPRIEDADE_INEXISTENTE("/propriedade-inexistente", "Propriedade inexistente"),
	PROPRIEDADE_IGNORADA("/propriedade-ignorada", "Propriedade ignorada"),
	URL_INVALIDA("/url-invalida", "URL inválida"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://modelopeticionamentoeletronico.com.br" + path;
		this.title = title;
	}
}
