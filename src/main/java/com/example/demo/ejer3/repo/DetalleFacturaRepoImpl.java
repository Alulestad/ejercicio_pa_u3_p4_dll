package com.example.demo.ejer3.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.ejer3.repo.modelo.DetalleFactura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class DetalleFacturaRepoImpl implements IDetalleFacturaRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(DetalleFactura detalleFactura) {
		this.entityManager.persist(detalleFactura);

	}

}
