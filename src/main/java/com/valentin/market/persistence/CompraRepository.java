package com.valentin.market.persistence;

import com.valentin.market.domain.Purchase;
import com.valentin.market.domain.repository.PurchaseRepository;
import com.valentin.market.persistence.crud.CompraCrudRepository;
import com.valentin.market.persistence.entity.Compra;
import com.valentin.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
	@Autowired
	CompraCrudRepository compraCrudRepository;

	@Autowired
	PurchaseMapper mapper;

	@Override
	public List<Purchase> getAll() {
		return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
	}

	@Override
	public Optional<List<Purchase>> getByClient(String clientId) {
		return compraCrudRepository.findByIdCliente(clientId)
								   .map(compras -> mapper.toPurchases(compras));
	}

	@Override
	public Optional<Purchase> getById(Integer purchaseId) {
		return compraCrudRepository.findById(purchaseId).map(compra -> mapper.toPurchase(compra));
	}

	@Override
	public Purchase save(Purchase purchase) {
		Compra compra = mapper.toCompra(purchase);
		compra.getProductos().forEach(producto -> producto.setCompra(compra));

		return mapper.toPurchase(compraCrudRepository.save(compra));
	}

	@Override
	public void delete(Integer purchaseId) {
		compraCrudRepository.deleteById(purchaseId);
	}
}
