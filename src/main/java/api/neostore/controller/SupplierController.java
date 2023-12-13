package api.neostore.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import api.neostore.exception.ResourceException;
import api.neostore.model.Supplier;
import api.neostore.service.SupplierService;

@Path("fornecedor")
public class SupplierController {
	private SupplierService supplierService;
	private final int MAX_RECORDS_PER_PAGE = 5;

	@Inject
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getSuppliersList(@QueryParam(value = "page") String page) {
		if (page == null) {
			throw new ResourceException(Status.BAD_REQUEST, "Preencha o número da página ('queryParam: page')");
		}
		int pageInt = Integer.parseInt(page);
		if (pageInt <= 0) {
			throw new ResourceException(Status.BAD_REQUEST, "O número da página deve ser maior que zero");
		}
		pageInt--;
		HashMap<String, Object> result = new HashMap<>();
		result.put("records", supplierService.findAll(pageInt, this.MAX_RECORDS_PER_PAGE));
		result.put("total_records", supplierService.getTotalRecords());
		return result;
	}

	@GET
	@Path("{supplierId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Supplier getSupplier(@PathParam(value = "supplierId") Long supplierId) {
		return supplierService.findById(supplierId)
			.orElseThrow(() -> new ResourceException(Status.NOT_FOUND, "Fornecedor não encontrado"));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Supplier createSupplier(@Valid Supplier supplier) {
		return supplierService.store(supplier);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("from_json")
	public Supplier[] createSupplierFromJson(@Valid Supplier[] suppliers) {
		return supplierService.storeFromArray(suppliers);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{supplierId}")
	public Supplier updateSupplier(@PathParam(value = "supplierId") Long supplierId, @Valid Supplier supplier) {
		return supplierService.findById(supplierId).map(sup -> {
			sup.setName(supplier.getName());
			sup.setEmail(supplier.getEmail());
			sup.setDescription(supplier.getDescription());
			sup.setCnpj(supplier.getCnpj());
			return supplierService.update(sup);
		}).orElseThrow(() -> new ResourceException(Status.NOT_FOUND, "Fornecedor não encontrado"));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{supplierId}")
	public HashMap<String, Boolean> deleteSupplier(@PathParam(value = "supplierId") Long supplierId) {
		return supplierService.findById(supplierId).map(p -> {
			supplierService.deleteById(supplierId);
			HashMap<String, Boolean> status = new HashMap<String, Boolean>();
			status.put("success", true);
			return status;
		}).orElseThrow(() -> new ResourceException(Status.NOT_FOUND, "Fornecedor não encontrado"));
	}
}