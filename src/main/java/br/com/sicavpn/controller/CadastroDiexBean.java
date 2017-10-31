package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.context.RequestContext;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.service.CadastroDiexService;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDiexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OmRepository omRepository;

	@Inject
	private CadastroDiexService cadastroDiexService;

	private List<Om> oms;

	private Om om;

	private Diex diex;

	private Conta conta;

	public void inicializar() {
		System.out.println("chamou o metoto inicializar dialog..");

		if (FacesUtil.isNotPostback()) {
			oms = omRepository.oms();
		}
	}
	
	public void abrirDialogo(){
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 370);
		
		RequestContext.getCurrentInstance().openDialog("/diex/cadastrodiex", opcoes, null);
	}

	public CadastroDiexBean() {
		limpar();
		System.out.println("Passou aqui no construtor....");
	}

	public void limpar() {
		diex = new Diex();
	}

	public void salvar() {
		diex = cadastroDiexService.salvar(diex);
		limpar();
		FacesUtil.addInfoMessage("Diex salvo com sucesso.");
	}

	public Conta getConta() {
		return conta;
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

}
