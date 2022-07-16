package acme.features.authenticated.epicure;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Epicure;

@Controller

public class AuthenticatedEpicureController extends AbstractController<Authenticated, Epicure> {

	@Autowired
	protected AuthenticatedEpicureGenerationService generate;
	
	@Autowired
	protected AuthenticatedEpicureUpdateService update;

	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.generate);
		super.addCommand("update", this.update);
	}

}
