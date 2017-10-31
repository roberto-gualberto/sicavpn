package br.com.sicavpn.model;

public enum Status {

	ATIVO("Ativo"), BLOQUEADO("Bloqueado");

	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
