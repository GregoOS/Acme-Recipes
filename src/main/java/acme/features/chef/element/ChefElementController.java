package acme.features.chef.element;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.element.Element;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefElementController extends AbstractController<Chef, Element> {

	@Autowired
	protected ChefElementListService list;

	@Autowired
	protected ChefElementShowService show;

	@Autowired
	protected ChefElementCreateService create;

	@Autowired
	protected ChefElementUpdateService update;

	@Autowired
	protected ChefElementPublishService publish;

	@Autowired
	protected ChefElementDeleteService delete;

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.show);
		super.addCommand("list", this.list);
		super.addCommand("create", this.create);
		super.addCommand("update", this.update);
		super.addCommand("delete", this.delete);
		super.addCommand("publish", "update", this.publish);
	}
}
