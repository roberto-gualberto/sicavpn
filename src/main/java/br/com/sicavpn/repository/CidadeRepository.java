package br.com.sicavpn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.sicavpn.model.Cidade;
import br.com.sicavpn.model.Conta;
import br.com.sicavpn.model.Diex;
import br.com.sicavpn.model.Estado;
import br.com.sicavpn.model.Om;

public class CidadeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}

	public List<Cidade> cidades() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	public List<Cidade> cidadeDe(Estado estado) {
		return manager.createQuery("from Cidade where estado = :estado", Cidade.class).setParameter("estado", estado).getResultList();
	}

//	public Diex porDescricao(String descricao) {
	//	try {
		//	return manager.createQuery("from Diex where upper(descricao) = :descricao", Diex.class)
		//		.setParameter("descricao", descricao.toUpperCase())
		//		.getSingleResult();
		//} catch (NoResultException e) {
		//	return null;
	//	}
	//}

//	public Diex guardar(Diex diex) {
		//return manager.merge(diex);
	//}
}
