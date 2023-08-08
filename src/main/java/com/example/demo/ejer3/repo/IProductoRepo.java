package com.example.demo.ejer3.repo;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;

import com.example.demo.ejer3.repo.modelo.Producto;

public interface IProductoRepo {
	
	public void insertar(Producto producto);
	
	public void actualizar(Producto producto);
	
	public Producto seleccionarPorCodigoBarras(String codigoBarras);
	
	
}
