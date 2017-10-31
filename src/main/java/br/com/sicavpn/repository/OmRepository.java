package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Om;
import br.com.sicavpn.repository.filter.ContaFilter;
import br.com.sicavpn.repository.filter.OmFilter;

public class OmRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Om porId(Long id) {
		return manager.find(Om.class, id);
	}

	public List<Om> oms() {
		return manager.createQuery("from Om", Om.class).getResultList();
	}
	
	public List<Estado> estados() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	public List<Om> porNome(String nome) {
		return manager.createQuery("from Om where upper(nome) like :nome ", Om.class)
				.setParameter("nome", nome.toUpperCase()+"%").getResultList();
	}
	
	public Om porDescricao(String nome) {
		try {
			return manager.createQuery("from Om where upper(nome) = :nome", Om.class)
				.setParameter("nome", nome.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Om guardar(Om om) {
		return manager.merge(om);
	}

	@SuppressWarnings("unchecked")
	public List<Om> filtradas(OmFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Om.class);
		
		if(filtro.getEstado()!=null){
			criteria.add(Restrictions.eq("estado", filtro.getEstado()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("estado")).list();
	}


	
	public List<Om> omDe(Cidade cidade) {
		return manager.createQuery("from Om where cidade = :cidade", Om.class).setParameter("cidade", cidade).getResultList();
	}

}
