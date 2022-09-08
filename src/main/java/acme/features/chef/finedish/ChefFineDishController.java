package acme.features.chef.finedish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.finedish.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefFineDishController extends AbstractController<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishListService list;

	@Autowired
	protected ChefFineDishListProposedService listProposed;

	@Autowired
	protected ChefFineDishShowService show;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.show);
		super.addCommand("list", this.list);
		super.addCommand("list-proposed", "list", this.listProposed);
	}
}