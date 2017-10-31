package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.sicavpn.model.Grupo;
import br.com.sicavpn.model.PasswordGenerator;
import br.com.sicavpn.model.PostoGraduacao;
import br.com.sicavpn.model.Status;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.GrupoRepository;
import br.com.sicavpn.repository.PostoGraduacaoRepository;
import br.com.sicavpn.service.CadastroUsuarioService;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PostoGraduacaoRepository postoGraduacaoRepository;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private GrupoRepository grupoRepository;

	private Usuario usuario;

	private List<PostoGraduacao> postosGraduacaos;

	private PostoGraduacao postoGraduacao;

	private List<Grupo> grupos;

	private Grupo grupo;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}

	public void inicializar() {
		System.out.println("chamou o metoto inicializar cadastroUsuarioBean..");
		if (FacesUtil.isNotPostback()) {
			postosGraduacaos = postoGraduacaoRepository.listaPostoGraduacao();
			grupos = grupoRepository.grupos();
		}
	}

	public Status[] getStatus() {
		return Status.values();
	}

	public void salvar() {
		//usuario.setSenha(PasswordGenerator.md5(usuario.getSenha()));
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		// limpar();
		FacesUtil.addInfoMessage("Usuario salvo com sucesso.");
	}
	
	public void editar() {
		usuario.setSenha(PasswordGenerator.md5(usuario.getSenha()));
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		// limpar();
		FacesUtil.addInfoMessage("Usuario salvo com sucesso.");
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PostoGraduacao> getPostosGraduacaos() {
		return postosGraduacaos;
	}

	public void setPostosGraduacaos(List<PostoGraduacao> postosGraduacaos) {
		this.postosGraduacaos = postosGraduacaos;
	}

	public PostoGraduacao getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(PostoGraduacao postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}

	// public PostoGraduacaoRepository getPostoGraduacaoRepository() {
	// return postoGraduacaoRepository;
	// }

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

}
