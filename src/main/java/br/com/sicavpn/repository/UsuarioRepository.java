package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sicavpn.model.Usuario;
import br.com.sicavpn.repository.filter.UsuarioFilter;
import br.com.sicavpn.service.NegocioException;
import br.com.sicavpn.util.jpa.Transactional;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> usuarios() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario porIdentidade(String identidade) {
		try {
			return manager.createQuery("from Usuario where upper(identidade) = :identidade", Usuario.class)
					.setParameter("identidade", identidade).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario nao pode ser removido");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(filtro.getEmail())) {
			criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getIdentidade())) {
			criteria.add(Restrictions.eq("identidade", filtro.getIdentidade()));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();

		} catch (NoResultException e) {
			// 
		}
		return usuario;
	}
	
	public List<Usuario> usuarioCadastroConta() {
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}
	
	public List<Usuario> logado(Long id) {
		return this.manager.createQuery("from Usuario where id =:id",Usuario.class).setParameter("id", id).getResultList();
		
	}
}
