package br.com.sicavpn.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sicavpn.model.PostoGraduacao;
import br.com.sicavpn.repository.PostoGraduacaoRepository;
import br.com.sicavpn.util.jpa.Transactional;

public class CadastroPostoGraduacaoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PostoGraduacaoRepository postoGraduacaoRepository;
	
	@Transactional
	public PostoGraduacao salvar(PostoGraduacao postoGraduacao) {
		PostoGraduacao postoGraduacaoExistente = postoGraduacaoRepository.porDescricao(postoGraduacao.getDescricao());
		
		if (postoGraduacaoExistente != null) {
			throw new NegocioException("Já existe um Posto/Graduacao com a descricao informada.");
		}
		
		return postoGraduacaoRepository.guardar(postoGraduacao);
	}

}
