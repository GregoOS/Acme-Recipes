package acme.features.chef.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandum.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefMemorandumController extends AbstractController<Chef, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumListService list;

	@Autowired
	protected ChefMemorandumListByFineDishService listByFineDish;

	@Autowired
	protected ChefMemorandumShowService show;

	@Autowired
	protected ChefMemorandumCreateService create;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);

		super.addCommand("list-by-finedish", "list", this.listByFineDish);
		super.addCommand("create", this.create);
	}

}
