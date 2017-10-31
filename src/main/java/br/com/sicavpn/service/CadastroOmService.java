package br.com.sicavpn.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.util.jpa.Transactional;

public class CadastroOmService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OmRepository omRepository;
	
	@Transactional
	public Om salvar(Om om) {
		Om omExistente = omRepository.porDescricao(om.getNome());
		
		if (omExistente != null) {
			throw new NegocioException("Já existe uma Om com o nome informado.");
		}
		
		return omRepository.guardar(om);
	}

}
