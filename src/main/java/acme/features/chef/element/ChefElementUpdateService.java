package acme.features.chef.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.element.Type;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;
import acme.utility.TextValidator;

@Service
public class ChefElementUpdateService implements AbstractUpdateService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;

	@Autowired
	protected TextValidator textValidator;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		boolean result;
		int id;
		Element element;

		id = request.getModel().getInteger("id");
		element = this.repository.findElementById(id);

		result = (element.isDraft() && request.isPrincipal(element.getChef()));

		return result;
	}

	@Override
	public Element findOne(final Request<Element> request) {
		Element res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findElementById(id);

		return res;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link", "type", "amountUnit");
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("name")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getName()), 
				"name", "chef.element.error.spam");
		}

		if (!errors.hasErrors("code")) {
			Element existing;
			Integer id;

			existing = this.repository.findOneElementByCode(entity.getCode());
			id = request.getModel().getInteger("id");

			errors.state(request, existing == null || existing.getId() == id, "code",
					"chef.element.error.duplicated");
		}

		if (!errors.hasErrors("retailPrice")) {
			Double retailPrice;
			String currency;

			retailPrice = entity.getRetailPrice().getAmount();
			currency = entity.getRetailPrice().getCurrency();

			errors.state(request, this.repository.isAcceptedCurrency(currency), "retailPrice",
					"chef.element.error.notacceptedcurrency");
			errors.state(request, retailPrice > 0.0, "retailPrice", "chef.element.error.negativeprice");
		}

		if (!errors.hasErrors("description")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getDescription()), "description",
					"chef.element.error.spam");
		}

		if (!errors.hasErrors("amountUnit")) {
			if (entity.getType() == Type.UTENSIL) {
				errors.state(request, entity.getAmountUnit().compareTo("") == 0, "amountUnit",
						"chef.element.error.amountunitut");
			} else {
				errors.state(request, entity.getAmountUnit().compareTo("") != 0, "amountUnit",
						"chef.element.error.amountuniting");
			}
		}
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type", "draft",
				"amountUnit");
	}

	@Override
	public void update(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
