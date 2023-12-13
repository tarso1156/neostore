package api.neostore.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import api.neostore.controller.SupplierController;
import api.neostore.repo.SupplierRepository;
import api.neostore.service.SupplierService;
import api.neostore.service.impl.SupplierServiceImpl;

public class AppConfig extends ResourceConfig {
	public AppConfig() {
		register(CORSResponseFilter.class);
		register(SupplierController.class);
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(SupplierServiceImpl.class).to(SupplierService.class);
				bind(SupplierRepository.class).to(SupplierRepository.class);
			}
		});
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}