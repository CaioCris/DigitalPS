package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNotFoundException;

public abstract class GenericDAOImpl <T,K> implements GenericDAO<T, K>{
	
	private EntityManager em;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void cadastrar(T entidade) {
		em.persist(entidade);
	}

	@Override
	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	@Override
	public void remover(K chave) throws IdNotFoundException {
		T entidade = buscar(chave);
		if (entidade == null)
			throw new IdNotFoundException("Entidade não encontrada");
		em.remove(entidade);
	}

	@Override
	public T buscar(K chave) {
		return em.find(clazz, chave);
	}

	@Override
	public List<T> listar() {
		return em.createQuery("from " + clazz.getName(),clazz).getResultList();
	}

	@Override
	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		}catch(Exception error){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			error.printStackTrace();
			throw new CommitException("Erro ao gravar");
		}
	}	
}
