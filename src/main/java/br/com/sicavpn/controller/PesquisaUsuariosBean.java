package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sicavpn.model.Grupo;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.GrupoRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.repository.filter.UsuarioFilter;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;

	private UsuarioFilter filtro;
	
	private List<Usuario> usuariosFiltrados;
	
	private Usuario usuarioSelecionado;

	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarioRepository.filtrados(filtro);
	}
	
	public void excluir(){
		usuarioRepository.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuario "+usuarioSelecionado.getNome()+" excluido com sucesso.");
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	
	
}
