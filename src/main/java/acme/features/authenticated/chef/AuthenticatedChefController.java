package acme.features.authenticated.chef;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Chef;

@Controller

public class AuthenticatedChefController extends AbstractController<Authenticated, Chef> {

	@Autowired
	protected AuthenticatedChefGenerationService generate;
	
	@Autowired
	protected AuthenticatedChefUpdateService update;

	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.generate);
		super.addCommand("update", this.update);
	}

}
