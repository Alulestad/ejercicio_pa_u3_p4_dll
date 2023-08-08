package com.example.demo.ejer3.repo;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.ejer3.repo.modelo.Factura;

public interface IFacturaRepo {

	
	public void insertar(Factura factura);
	
	public List<Factura> seleccionarPorFecha(LocalDateTime fecha);
	
}
