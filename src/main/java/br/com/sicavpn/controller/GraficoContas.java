package br.com.sicavpn.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

@Named
@RequestScoped
public class GraficoContas implements Serializable {

	private static final long serialVersionUID = 1L;

	private PieChartModel pieModel1;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	private void createPieModels() {
		createPieModel1();
	}

	private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("CMA", 15);
        pieModel1.set("4 CTA", 10);
        pieModel1.set("CIGS", 7);
        pieModel1.set("1 BIS", 6);
         
        pieModel1.setTitle("Contas Criadas");
        pieModel1.setLegendPosition("w");
    }
}
