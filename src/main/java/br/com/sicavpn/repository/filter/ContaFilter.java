package br.com.sicavpn.repository.filter;

import java.io.Serializable;

import br.com.sicavpn.model.Om;

public class ContaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Om om;
	private String nome;
	private String identidade;

		
	public Om getOm() {
		return om;
	}

	public void setOm(Om om) {
		this.om = om;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

}
