package br.com.sicavpn.model;

public enum TipoPostoGraduacao {
	
	GENERAL("General"),
	CORONEL("Coronel"),
	TEN_CORONEL("Tenente Coronel"), 
	MAJOR("Major"),
	CAPITAO("Capitao"),
	PRIMEIRO_TEN("1 Tenente"),
	SEGUNDO_TEN("2 Tenente"),
	ASPIRANTE("Aspirante"),
	SUBOFICIAL("Sub-Tenente"),
	PRIMEIRO_SGT("1 Sargento"),
	SEGUNDO_SGT("2 Sargento"),
	TERCEIRO_SGT("3 Sargento"),
	SC("Servidor Civil");
	
	private String descricao;
	
	private TipoPostoGraduacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
