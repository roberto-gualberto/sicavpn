package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.PUBLIC_MEMBER;

import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.filter.ContaFilter;
import br.com.sicavpn.service.NegocioException;
import br.com.sicavpn.util.jpa.Transactional;

public class ContaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Conta porId(Long id) {
		return manager.find(Conta.class, id);
	}

	public Conta guardar(Conta conta) {
		return manager.merge(conta);
	}

	public Conta porIdentidade(String identidade) {
		try {
			return manager.createQuery("from Conta where upper(identidade) = :identidade", Conta.class)
					.setParameter("identidade", identidade).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Conta> filtradas(ContaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Conta.class);

		if (filtro.getOm() != null) {
			criteria.add(Restrictions.eq("om", filtro.getOm()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getIdentidade())) {
			criteria.add(Restrictions.eq("identidade", filtro.getIdentidade()));
		}

		System.out.println("TOTAL DE CONTAS -----> " + criteria.list().size());
		return criteria.addOrder(Order.asc("nome")).list();
	}

	@Transactional
	public void remover(Conta conta) {
		try {
			conta = porId(conta.getId());
			manager.remove(conta);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Conta nao pode ser removida");
		}

	}

}
