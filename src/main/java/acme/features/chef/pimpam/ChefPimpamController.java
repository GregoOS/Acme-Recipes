package acme.features.chef.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpam.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPimpamController extends AbstractController<Chef, Pimpam> {
	
	@Autowired
	protected ChefPimpamListService chefPimpamListService;
	
	@Autowired
	protected ChefPimpamShowService chefPimpamShowService;
	
	@Autowired
	protected ChefPimpamCreateService chefPimpamCreateService;
	
	@Autowired
	protected ChefPimpamUpdateService chefPimpamUpdateService;
	
	@Autowired
	protected ChefPimpamDeleteService chefPimpamDeleteService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.chefPimpamListService);
		super.addCommand("show", this.chefPimpamShowService);
		
		super.addCommand("create", this.chefPimpamCreateService);
		super.addCommand("update", this.chefPimpamUpdateService);
		super.addCommand("delete", this.chefPimpamDeleteService);
	}

}
