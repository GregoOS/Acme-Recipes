package acme.features.any.element;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.element.Element;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;


@Controller
public class AnyElementController extends AbstractController<Any, Element> {

	@Autowired
	protected AnyElementListService list;
	
	@Autowired
	protected AnyElementShowService show;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.show);
		super.addCommand("list", this.list);
	}
}
