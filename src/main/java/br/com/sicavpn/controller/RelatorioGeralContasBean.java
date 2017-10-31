package br.com.sicavpn.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.primefaces.context.RequestContext;

import br.com.sicavpn.util.jsf.FacesUtil;
import br.com.sicavpn.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioGeralContasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
		
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;
	
	
	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("height", 220);
		opcoes.put("width", 630);

		RequestContext.getCurrentInstance().openDialog("/relatorios/relatoriogeralcontas", opcoes, null);
	}
	
	public void emitir() {
		Map<String, Object> parametro = new HashMap<>();
		parametro.put("geral", "sim");
		Date date = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");  
		String data = formatador.format(date);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/contasgeral.jasper",
				this.response, parametro, "contasgeral"+data+".pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

		
}
