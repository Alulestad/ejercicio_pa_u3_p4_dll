package com.example.demo.ejer3.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.ejer3.repo.IDetalleFacturaRepo;
import com.example.demo.ejer3.repo.IFacturaRepo;
import com.example.demo.ejer3.repo.IProductoRepo;
import com.example.demo.ejer3.repo.modelo.DetalleFactura;
import com.example.demo.ejer3.repo.modelo.Factura;
import com.example.demo.ejer3.repo.modelo.Producto;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class FacturaServiceImpl implements IFacturaService {

	private static final Logger LOG=LoggerFactory.getLogger(FacturaServiceImpl.class);
	
	@Autowired
	private IProductoRepo iProductoRepo;
	
	@Autowired
	private IDetalleFacturaRepo iDetalleFacturaRepo;
	
	@Autowired
	private IFacturaRepo iFacturarRepo;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void facturar(List<Producto> productos, String cedula) {
		List<Producto> productosEncontrados=new ArrayList<>();
		Factura factura= new Factura();
		List<DetalleFactura> listaDetalles= new ArrayList<>();
		BigDecimal total= new BigDecimal(0);
		
		for(Producto pro:productos) {
			Producto productoEncontrado=this.iProductoRepo.seleccionarPorCodigoBarras(pro.getCodigoBarras());
			if (productoEncontrado==null) {
				LOG.info("no se puede hacer la compra");
				return;
			}
			
			
			productoEncontrado.setDetalleFacturas(listaDetalles);
			productosEncontrados.add(productoEncontrado);
			
			
			DetalleFactura detalle1=new DetalleFactura();
			detalle1.setCantidad(pro.getCantidad());
			detalle1.setFactura(factura);
			detalle1.setPrecioUnitario(productoEncontrado.getPrecio());
			BigDecimal subtotal= productoEncontrado.getPrecio().multiply(new BigDecimal(pro.getCantidad()));
			detalle1.setProducto(productoEncontrado);
			detalle1.setSubtotal(subtotal);
			total=total.add(subtotal);
			listaDetalles.add(detalle1);
			
		}
		
		
		factura.setDetalleFacturas(listaDetalles);
		factura.setFecha(LocalDateTime.now());
		factura.setTotal(total);
		
		this.iFacturarRepo.insertar(factura);

	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Factura> reporteFacturas(LocalDateTime fecha, String categoriaProducto, Integer cantidad) {
		
		List<Factura> listaFacturas= this.iFacturarRepo.seleccionarPorFecha(fecha);
		
		//listaFacturas.forEach((d)->LOG.info(d.toString()));
		
		
		return listaFacturas;
	}

}
