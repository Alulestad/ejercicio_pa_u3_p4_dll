package com.example.demo.ejer3.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.ejer3.repo.modelo.Factura;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FacturaRepoImpl implements IFacturaRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Factura factura) {
		this.entityManager.persist(factura);

	}

	@Override
	public List<Factura> seleccionarPorFecha(LocalDateTime fecha) {
		Query query=this.entityManager.createNativeQuery("select * from factura f where f.fact_fecha=:datoFecha", Factura.class);
		query.setParameter("datoFecha", fecha);
		
		List<Factura> lista=query.getResultList();
		
		return lista;
	}

}
