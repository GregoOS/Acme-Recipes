package acme.features.administrator.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemconfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository {
	
	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();	

}
