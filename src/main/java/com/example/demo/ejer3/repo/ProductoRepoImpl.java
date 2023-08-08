package com.example.demo.ejer3.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.ejer3.repo.modelo.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ProductoRepoImpl implements IProductoRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Producto producto) {
		this.entityManager.persist(producto);

	}

	@Override
	public void actualizar(Producto producto) {
		this.entityManager.merge(producto);
		
	}

	@Override
	public Producto seleccionarPorCodigoBarras(String codigoBarras) {

		TypedQuery<Producto> myQuery=this.entityManager.createQuery("select p from Producto p where p.codigoBarras=:datoCodigoBarras ",Producto.class);
		myQuery.setParameter("datoCodigoBarras", codigoBarras);
		
		Producto producto=null;
		try {
			producto=myQuery.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return producto;
	}
	
	

}
