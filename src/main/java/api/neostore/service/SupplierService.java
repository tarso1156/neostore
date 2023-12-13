package api.neostore.service;

import java.util.List;
import java.util.Optional;

import api.neostore.model.Supplier;

public interface SupplierService {
	Supplier store(Supplier supplier);

	Supplier[] storeFromArray(Supplier[] suppliers);

	Supplier update(Supplier supplier);

	void deleteById(Long id);

	Optional<Supplier> findById(Long id);

	Optional<Supplier> findByCnpj(String cnpj);

	long getTotalRecords();

	List<Supplier> findAll(int page, int maxResults);
}
