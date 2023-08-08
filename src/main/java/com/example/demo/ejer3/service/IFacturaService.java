package com.example.demo.ejer3.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.ejer3.repo.modelo.Factura;
import com.example.demo.ejer3.repo.modelo.Producto;

public interface IFacturaService {

	public void facturar(List<Producto> productos, String cedula);
	
	public List<Factura> reporteFacturas(LocalDateTime fecha, String categoriaProducto, Integer cantidad);
	
	
	
	
}
