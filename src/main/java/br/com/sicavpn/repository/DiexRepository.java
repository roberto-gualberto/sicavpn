package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Om;

public class DiexRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Diex porId(Long id) {
		return manager.find(Diex.class, id);
	}

	public List<Diex> diexs() {
		return manager.createQuery("from Diex", Diex.class).getResultList();
	}

	public List<Diex> diexDe(Om om) {
		return manager.createQuery("from Diex where om = :om", Diex.class).setParameter("om", om).getResultList();
	}

	public Diex porDescricao(String descricao) {
		try {
			return manager.createQuery("from Diex where upper(descricao) = :descricao", Diex.class)
				.setParameter("descricao", descricao.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Diex guardar(Diex diex) {
		return manager.merge(diex);
	}
}
