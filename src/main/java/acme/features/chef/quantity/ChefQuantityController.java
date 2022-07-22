package acme.features.chef.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantity.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefQuantityController extends AbstractController<Chef, Quantity> {

	@Autowired
	protected ChefQuantityListService list;

	@Autowired
	protected ChefQuantityShowService show;

	@Autowired
	protected ChefQuantityCreateService create;

	@Autowired
	protected ChefQuantityUpdateService update;

	@Autowired
	protected ChefQuantityDeleteService delete;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);
		super.addCommand("create", this.create);
		super.addCommand("update", this.update);
		super.addCommand("delete", this.delete);
	}
}
