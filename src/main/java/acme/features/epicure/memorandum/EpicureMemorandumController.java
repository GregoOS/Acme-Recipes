package acme.features.epicure.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandum.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureMemorandumController extends AbstractController<Epicure, Memorandum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumListService list;

	@Autowired
	protected EpicureMemorandumListByFineDishService listByFineDish;

	@Autowired
	protected EpicureMemorandumShowService show;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);

		super.addCommand("list-by-finedish", "list", this.listByFineDish);
	}

}
