package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.sicavpn.model.PostoGraduacao;

public class PostoGraduacaoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public PostoGraduacao porId(Long id) {
		return manager.find(PostoGraduacao.class, id);
	}

	public List<PostoGraduacao> listaPostoGraduacao() {
		return manager.createQuery("from PostoGraduacao", PostoGraduacao.class).getResultList();
	}

	public PostoGraduacao porDescricao(String descricao) {
		try {
			return manager.createQuery("from PostoGraduacao where upper(descricao) = :descricao", PostoGraduacao.class)
					.setParameter("descricao", descricao.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public PostoGraduacao guardar(PostoGraduacao postoGraduacao) {

		return manager.merge(postoGraduacao);
	}
}
