package br.com.sicavpn.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.primefaces.context.RequestContext;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.repository.CidadeRepository;
import br.com.sicavpn.repository.EstadoRepository;
import br.com.sicavpn.repository.OmRepository;
import br.com.sicavpn.util.jsf.FacesUtil;
import br.com.sicavpn.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class RelatorioContasOmBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	@Inject
	private CidadeRepository cidadeRepository;

	@Inject
	private OmRepository omRepository;

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

	public void carregarCidades() {
		cidades = cidadeRepository.cidadeDe(om.getEstado());
	}

	public void carregarOms() {
		System.out.println("chamou o metoto garregar oms");
		oms = omRepository.omDe(om.getCidade());
	}

	public RelatorioContasOmBean() {
		om = new Om();
	}

	public void limpar() {
		// conta = new Conta();
		cidades = new ArrayList<>();
	}

	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("height", 350);
		opcoes.put("width", 630);

		RequestContext.getCurrentInstance().openDialog("/relatorios/relatorioporom", opcoes, null);
	}

	public void emitir() {
		System.out.println("Chamou o metoto emitir");
		System.out.println("Estado passada como parametro ----> " + this.om.getCidade().getEstado().getNome());
		System.out.println("cidade passada como parametro ----> " + this.om.getCidade().getNome().toString());
		System.out.println("OM nome   ... " + this.om.getNome().toString());

		String om = this.om.getNome().toString();

		Map<String, Object> parametro = new HashMap<>();
		parametro.put("om", om);
		Date date = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
		String data = formatador.format(date);

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/contasporom.jasper", this.response, parametro,
				"contasporom-" + om + "-" + data + ".pdf");

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			FacesUtil.addInfoMessage("Relatório gerado com sucesso.");
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

	public List<Om> getOms() {
		return oms;
	}

	public Om getOm() {
		return om;
	}

	public void setOm(Om om) {
		this.om = om;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

}
