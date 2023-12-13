package api.neostore.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import api.neostore.model.Supplier;

public class SupplierRepository {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("NeoStore");
	private EntityManager em;

	public SupplierRepository() {
		em = emf.createEntityManager();
	}

	public Supplier store(Supplier supplier) {
		em.getTransaction().begin();
		em.persist(supplier);
		em.getTransaction().commit();
		return supplier;
	}

	public Supplier[] storeFromArray(Supplier[] suppliers) {
		em.getTransaction().begin();
		for (Supplier supplier : suppliers) {
			em.persist(supplier);
		}
		em.getTransaction().commit();
		return suppliers;
	}

	public Optional<Supplier> findById(Long id) {
		em.getTransaction().begin();
		Supplier supplier = em.find(Supplier.class, id);
		em.getTransaction().commit();
		return supplier != null ? Optional.of(supplier) : Optional.empty();
	}
	
	public Optional<Supplier> findByCnpj(String cnpj) {
		em.getTransaction().begin();
		Supplier supplier = null;
		try {
			supplier = (Supplier) em.createQuery("FROM Supplier WHERE cnpj = :cnpj").setParameter("cnpj", cnpj).getSingleResult();
		} catch (Exception e) {}
		em.getTransaction().commit();
		return supplier != null ? Optional.of(supplier) : Optional.empty();
	}

	public long getTotalRecords() {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT COUNT(1) FROM Supplier");
		em.getTransaction().commit();
		return (long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> findAll(int offset, int maxRecords) {
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Supplier ORDER BY id DESC");
		query.setFirstResult(offset);
		query.setMaxResults(maxRecords);
		em.getTransaction().commit();
		return query.getResultList();
	}

	public Supplier update(Supplier supplier) {
		em.getTransaction().begin();
		supplier = em.merge(supplier);
		em.getTransaction().commit();
		return supplier;
	}

	public void deleteById(Long id) {
		em.getTransaction().begin();
		em.remove(em.find(Supplier.class, id));
		em.getTransaction().commit();
	}

	public void close() {
		emf.close();
	}
}
