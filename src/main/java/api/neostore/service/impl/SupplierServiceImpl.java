package api.neostore.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import api.neostore.model.Supplier;
import api.neostore.repo.SupplierRepository;
import api.neostore.service.SupplierService;;

public class SupplierServiceImpl implements SupplierService {
	private SupplierRepository supplierRepository;

	@Inject
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Supplier store(Supplier supplier) {
		return supplierRepository.store(supplier);
	}

	@Override
	public Supplier[] storeFromArray(Supplier[] suppliers) {
		return supplierRepository.storeFromArray(suppliers);
	}

	@Override
	public void deleteById(Long id) {
		supplierRepository.deleteById(id);
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return supplierRepository.findById(id);
	}
	
	@Override
	public Optional<Supplier> findByCnpj(String cnpj) {
		return supplierRepository.findByCnpj(cnpj);
	}

	@Override
	public long getTotalRecords() {
		return supplierRepository.getTotalRecords();
	}

	@Override
	public List<Supplier> findAll(int page, int maxRecords) {
		return supplierRepository.findAll((page * maxRecords), maxRecords);
	}

	@Override
	public Supplier update(Supplier supplier) {
		return supplierRepository.update(supplier);
	}
}
