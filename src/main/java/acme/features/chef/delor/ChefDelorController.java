package acme.features.chef.delor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.delor.Delor;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefDelorController extends AbstractController<Chef, Delor> {
	
	@Autowired
	protected ChefDelorListService chefDelorListService;
	
	@Autowired
	protected ChefDelorShowService chefDelorShowService;
	
	@Autowired
	protected ChefDelorCreateService chefDelorCreateService;
	
	@Autowired
	protected ChefDelorUpdateService chefDelorUpdateService;
	
	@Autowired
	protected ChefDelorDeleteService chefDelorDeleteService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.chefDelorListService);
		super.addCommand("show", this.chefDelorShowService);
		
		super.addCommand("create", this.chefDelorCreateService);
		super.addCommand("update", this.chefDelorUpdateService);
		super.addCommand("delete", this.chefDelorDeleteService);
	}

}
