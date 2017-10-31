package br.com.sicavpn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.ContaRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.repository.UsuarioRepository;
import br.com.sicavpn.repository.filter.ContaFilter;
import br.com.sicavpn.repository.filter.OmFilter;
import br.com.sicavpn.repository.filter.UsuarioFilter;
import br.com.sicavpn.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaOmBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private OmRepository omRepository;

	private OmFilter filtro;
	
	private List<Om> omFiltradas;
	
	private List<Estado> estados;
	
	
	public PesquisaOmBean() {
		filtro = new OmFilter();
	}
	
	public void inicializar() {
		System.out.println("chamou o metoto inicializar PesquisaOMBean..");
		// conta = new Conta();
		if (FacesUtil.isNotPostback()) {
			estados = omRepository.estados();
			System.out.println("Listando om estados....");
		}
	}
	
	public void pesquisar() {
		omFiltradas = omRepository.filtradas(filtro);
	}

    
		
	public List<Om> getOmFiltradas() {
		return omFiltradas;
	}

	public OmFilter getFiltro() {
		return filtro;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}


}
