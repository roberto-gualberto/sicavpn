package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.repository.filter.ContaFilter;
import br.com.sicavpn.repository.filter.UsuarioFilter;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaContasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaRepository contaRepository;

	@Inject
	private OmRepository omRepository;

	private ContaFilter filtro;

	private Conta contaSelecionada;

	private List<Conta> contasFiltradas;

	private List<Om> oms;

	private Om om;

	private Integer total = 0;

	public PesquisaContasBean() {
		filtro = new ContaFilter();
	}

	public void inicializar() {
		System.out.println("chamou o metoto inicializar PesquisaContaBean..");
		// conta = new Conta();
		if (FacesUtil.isNotPostback()) {
			oms = omRepository.oms();
			System.out.println("Listando as oms....");
		}
	}

	public void pesquisar() {
		contasFiltradas = contaRepository.filtradas(filtro);
		total= contasFiltradas.size();
	}
	
	public Integer totalcontas(){
		return 1;
	}

	public void excluir() {
		contaRepository.remover(contaSelecionada);
		contasFiltradas.remove(contaSelecionada);

		FacesUtil.addInfoMessage("Conta " + contaSelecionada.getNome() + " excluida com sucesso.");
	}

	public List<Conta> getContasFiltradas() {
		return contasFiltradas;
	}

	public ContaFilter getFiltro() {
		return filtro;
	}

	public Om getOm() {
		return om;
	}

	public Conta getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Conta contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public void setOm(Om om) {
		this.om = om;
	}

	public List<Om> getOms() {
		return oms;
	}

	public Integer getTotal() {
		
		return total;
	}

	
}
