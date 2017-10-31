package br.com.sicavpn.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.sicavpn.model.Grupo;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UsuarioRepository usuarios = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarios.porEmail(email);
		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupo(usuario));
			System.out.println("STATUS USUARIO ---------->1 " + usuario.getStatus());
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupo(Usuario usuario) {

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Grupo grupo = new Grupo();
		grupo = usuario.getGrupo();
		if (usuario.getStatus().equals(usuario.getStatus().ATIVO)) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}else{
			authorities = null;
		}
		return authorities;
	}

}
