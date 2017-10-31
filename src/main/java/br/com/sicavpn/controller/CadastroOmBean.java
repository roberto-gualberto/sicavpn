package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.context.RequestContext;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.model.PostoGraduacao;
import br.com.sicavpn.model.TipoPostoGraduacao;
import br.com.sicavpn.repository.CidadeRepository;
import br.com.sicavpn.repository.DiexRepository;
import br.com.sicavpn.repository.EstadoRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.repository.PostoGraduacaoRepository;
import br.com.sicavpn.service.CadastroContaService;
import br.com.sicavpn.service.CadastroOmService;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroOmBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeRepository cidadeRepository;
	
	@Inject
	private OmRepository omRepository;

	@Inject
	private CadastroOmService cadastroOmService;

	@Inject
	private EstadoRepository estadoRepository;

	private List<Om> oms;

	private List<Cidade> cidades;

	private List<Estado> estados;

	private Om om;

	private Estado estado;

	public void inicializar() {
		System.out.println("chamou o metoto inicializar cadastroOmBean..");
		if (FacesUtil.isNotPostback()) {
			estados = estadoRepository.listaEstado();
		}
	}
	
	public void edicaoOm() {
		System.out.println("chamou o metoto edicaoOM cadastroOmBean..");
		if (FacesUtil.isNotPostback()) {
			estados = estadoRepository.listaEstado();
			carregarCidades();
		}
	}

	public void carregarCidades() {
		cidades = cidadeRepository.cidadeDe(om.getEstado());
	}
	
	public void carregarOms() {
		System.out.println("chamou o metoto garregar oms");
		oms = omRepository.omDe(om.getCidade());
	}

	public CadastroOmBean() {
		om = new Om();
	}

	public void limpar() {
		// conta = new Conta();
		cidades = new ArrayList<>();
	}

	// public TipoPostoGraduacao[] getTipoPostoGraduacao() {
	// return TipoPostoGraduacao.values();
	// }

	public void teste() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void salvar() {

		System.out.println("Botao SALVAR....");
		System.out.println("OM.... "+this.om.getNome());
		System.out.println("OM.... "+this.om.getEstado().getNome().toString());
		System.out.println("OM.... "+this.om.getCidade().getNome().toString());
		this.om = cadastroOmService.salvar(this.om);
		// limpar();
		FacesUtil.addInfoMessage("Om salva com sucesso.");

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

	@NotNull
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@NotNull
	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

}
