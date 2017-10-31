package br.com.sicavpn.teste;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sicavpn.model.PasswordGenerator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class TesteSistema {

	public static void main(String[] args) {
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("SicavpnPU");
		// EntityManager manager = factory.createEntityManager();

		// EntityTransaction trx = manager.getTransaction();
		// trx.begin();

		// trx.commit();

		// PasswordGenerator pass = new PasswordGenerator();

		// System.out.println("Senha MD5 ----> "+PasswordGenerator.md5("123"));

		File jasperFile = new File("/home/gualberto/NetBeansProjects/Sicavpn/src/main/resources/relatorios/contasporom.jasper");
		try {

			JasperReport report = (JasperReport) JRLoader.loadObject(jasperFile);

			JRXmlWriter.writeReport(report, "/home/gualberto/NetBeansProjects/Sicavpn/src/main/resources/relatorios/contasporom.jrxml", "UTF-8");

		} catch (JRException e) {

			e.printStackTrace();

		}

	}

}