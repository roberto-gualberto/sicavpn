package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.event.SelectEvent;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.model.PostoGraduacao;
import br.com.sicavpn.model.TipoPostoGraduacao;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.repository.PostoGraduacaoRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.security.Seguranca;
import br.com.sicavpn.service.CadastroContaService;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroContaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OmRepository omRepository;

	@Inject
	private DiexRepository diexRepository;
	
	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private CadastroContaService cadastroContaService;

	@Inject
	private PostoGraduacaoRepository postoGraduacaoRepository;

	private List<Om> oms;

	private List<Diex> diexs;

	private List<PostoGraduacao> postosGraduacaos;

	private Om om;

	private Diex diex;

	private Conta conta;

	private PostoGraduacao postoGraduacao;
	
	private List<Usuario> usuario;

	
	
	public void inicializar() {
		System.out.println("chamou o metoto inicializar cadastrocontaBean..");
		// conta = new Conta();
		if (FacesUtil.isNotPostback()) {
			oms = omRepository.oms();
			postosGraduacaos = postoGraduacaoRepository.listaPostoGraduacao();
			usuarioLogado();
		}
	}
	
	
	public void usuarioLogado(){
		Seguranca usuarioLogado = new Seguranca();
		this.usuario = this.usuarioRepository.logado(usuarioLogado.getIdLogado().longValue());
	}
	
	public void edicaoConta(){
		System.out.println("chamou o metoto edicaoConta..");
		// conta = new Conta();
		if (FacesUtil.isNotPostback()) {
			this.usuario = this.usuarioRepository.usuarioCadastroConta();
			oms = omRepository.oms();
			postosGraduacaos = postoGraduacaoRepository.listaPostoGraduacao();
			carregarDiexs();
		}
	}

	public void carregarDiexs() {
		System.out.println("chamou o metoto garregar diexs");
		System.out.println("Nome OM passada para carregar DIEX = " + conta.getOm().getNome());
		System.out.println("OM passada para carregar DIEX = " + conta.getOm().toString());
		System.out.println("Id OM passada para carregar DIEX = " + conta.getOm().getId());
		diexs = diexRepository.diexDe(conta.getOm());
	}

	public List<Om> completarOm(String nome) {
		System.out.println("Nome passado para pesquisa..... = " + nome);
		return this.omRepository.porNome(nome);
	}

	public CadastroContaBean() {
		conta = new Conta();
	}

	public void limpar() {
		// conta = new Conta();
		diexs = new ArrayList<>();
	}

	public void salvar() {

		System.out.println("Botao SALVAR....");
		System.out.println("Conta OM = " + conta.getOm().getId());
		System.out.println("Conta Nome = " + conta.getNome());
		System.out.println("Conta Senha = " + conta.getSenha());
		System.out.println("Conta Identidade = " + conta.getIdentidade());
		System.out.println("ID usuario salvo na conta = " +conta.getUsuario().getId() );
		System.out.println("Conta Diex = " + conta.getDiex().getDescricao());
		this.conta = cadastroContaService.salvar(this.conta);
		// limpar();
		FacesUtil.addInfoMessage("Conta salva com sucesso.");
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Om> getOms() {
		return oms;
	}

	@NotNull
	public Om getOm() {
		return om;
	}

	public void setOm(Om om) {
		this.om = om;
	}

	public Diex getDiex() {
		return diex;
	}

	public void setDiex(Diex diex) {
		this.diex = diex;
	}

	public List<Diex> getDiexs() {
		return diexs;
	}

	public PostoGraduacao getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(PostoGraduacao postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}

	public List<PostoGraduacao> getPostosGraduacaos() {
		return postosGraduacaos;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	
	
}
