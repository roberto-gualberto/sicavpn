package br.com.sicavpn.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.PasswordGenerator;
import br.com.sicavpn.model.Status;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.porIdentidade(usuario.getIdentidade());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um Usuario com a identidade informada.");
		} else if (usuarioExistente == null) {
			usuario.setSenha(PasswordGenerator.md5(usuario.getSenha()));
			usuario.setDataCadastro(new Date());
		}

		return usuarioRepository.guardar(usuario);
	}
	
	@Transactional
	public Usuario editar(Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.porIdentidade(usuario.getIdentidade());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um Usuario com a identidade informada.");
		}
		
	//	usuario.setSenha(PasswordGenerator.md5(usuario.getSenha()));

		return usuarioRepository.guardar(usuario);
	}

}
