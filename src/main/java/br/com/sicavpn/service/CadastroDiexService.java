package br.com.sicavpn.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.util.jpa.Transactional;

public class CadastroDiexService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DiexRepository diexRepository;
	
	@Transactional
	public Diex salvar(Diex diex) {
		Diex diexExistente = diexRepository.porDescricao(diex.getDescricao());
		
		if (diexExistente != null) {
			throw new NegocioException("Já existe um diex com a descricao informada.");
		}
		
		return diexRepository.guardar(diex);
	}

}
