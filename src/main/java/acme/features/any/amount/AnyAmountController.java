package acme.features.any.amount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.amount.Amount;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyAmountController extends AbstractController<Any, Amount> {

	@Autowired
	protected AnyAmountListService list;

	@Autowired
	protected AnyAmountShowService show;

	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);
	}
}