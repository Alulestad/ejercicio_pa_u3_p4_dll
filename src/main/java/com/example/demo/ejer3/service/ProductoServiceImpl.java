package com.example.demo.ejer3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ejer3.repo.IProductoRepo;
import com.example.demo.ejer3.repo.modelo.Producto;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepo iProductoRepo;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void agregar(Producto producto) {
		
		Producto prodcutoEncontrado=null;
		
		
		prodcutoEncontrado=this.iProductoRepo.seleccionarPorCodigoBarras(producto.getCodigoBarras());
		
		
		if(prodcutoEncontrado==null) {
			//procedo a al insercion
			this.iProductoRepo.insertar(producto);
			
		}else {
			prodcutoEncontrado.setStock(prodcutoEncontrado.getStock()+ producto.getStock());
			this.iProductoRepo.actualizar(prodcutoEncontrado);
		}
		

	}

}
