package br.com.sicavpn.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.TipoPostoGraduacao;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.util.jpa.Transactional;

public class CadastroContaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaRepository contaRepository;

	@Transactional
	public Conta salvar(Conta conta) {
		Conta contaExistente = contaRepository.porIdentidade(conta.getIdentidade());

		if (contaExistente != null && !contaExistente.equals(conta)) {
			throw new NegocioException("Já existe uma conta com a  identidade informada.");
		} else if (contaExistente == null) {
			conta.setDataCadastro(new Date());
		}

		return contaRepository.guardar(conta);
	}

}
