package br.com.sicavpn.util.report;

import java.sql.Connection;
import java.sql.SQLException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

public class ExecutorRelatorio implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametro;
	private String nomeArquivoSaida;
	
	private boolean relatorioGerado;
	
	public ExecutorRelatorio(String caminhoRelatorio,
			HttpServletResponse response, Map<String, Object> parametro,
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametro = parametro;
		this.nomeArquivoSaida = nomeArquivoSaida;
		
		this.parametro.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
			
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametro, connection);
			this.relatorioGerado = print.getPages().size() > 0;
			
			if (this.relatorioGerado) {
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ this.nomeArquivoSaida  + "\"");
				
				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}