package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.sicavpn.model.Estado;

public class EstadoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	public List<Estado> listaEstado() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

}
