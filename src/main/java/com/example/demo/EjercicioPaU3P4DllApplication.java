package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.ejer3.repo.modelo.Factura;
import com.example.demo.ejer3.repo.modelo.Producto;
import com.example.demo.ejer3.service.FacturaServiceImpl;
import com.example.demo.ejer3.service.IFacturaService;
import com.example.demo.ejer3.service.IProductoService;

@SpringBootApplication
public class EjercicioPaU3P4DllApplication implements CommandLineRunner{

	private static final Logger LOG=LoggerFactory.getLogger(EjercicioPaU3P4DllApplication.class);
	
	@Autowired
	private IProductoService iProductoService;
	
	@Autowired
	private IFacturaService iFacturaService;
	
	public static void main(String[] args) {
		SpringApplication.run(EjercicioPaU3P4DllApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Producto prod1=new Producto();
		prod1.setCategoria("lacteos");
		prod1.setCodigoBarras("1234");
		prod1.setNombre("leche vita");
		prod1.setPrecio(new BigDecimal(1));
		prod1.setStock(1);
		
		this.iProductoService.agregar(prod1);
		this.iProductoService.agregar(prod1);
		
		Producto prod2=new Producto();
		prod2.setCategoria("carnes_mariscos");
		prod2.setCodigoBarras("1235");
		prod2.setNombre("libra camaron");
		prod2.setPrecio(new BigDecimal(9));
		prod2.setStock(1);
		this.iProductoService.agregar(prod2);
		this.iProductoService.agregar(prod2);
		
		
		Producto p1= new Producto();
		p1.setCantidad(1);
		p1.setCodigoBarras("1234");
		
		Producto p2= new Producto();
		p2.setCantidad(1);
		p2.setCodigoBarras("1235");
		
		List<Producto> productos= new ArrayList<>();
		productos.add(p1);
		productos.add(p2);
		
		this.iFacturaService.facturar(productos, "1712431243");
		
		List<Factura> facturas= this.iFacturaService.reporteFacturas(LocalDateTime.of(2023, 8, 7,19,36), "lacteos", 0);
		facturas.parallelStream().forEach((d)->LOG.info(d.toString()));
	}
	
	

}
