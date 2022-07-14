
package acme.features.chef.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefElementCreateService implements AbstractCreateService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;

	//@Autowired
	//protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		return true;
	}

	@Override
	public Element instantiate(final Request<Element> request) {
		assert request != null;

		Element res;
		Chef chef;
		int chefId;

		chefId = request.getPrincipal().getActiveRoleId();
		chef = this.repository.findOneChefById(chefId);

		res = new Element();
		res.setDraft(true);
		res.setChef(chef);

		return res;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link", "type");
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("name")) {
			//errors.state(request, !this.spamService.isSpam(entity.getName()), "name", "chef.element.error.spam");
		}

		if (!errors.hasErrors("code")) {
			Element existing;

			existing = this.repository.findOneElementByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.element.error.duplicated");
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
			//errors.state(request, !this.spamService.isSpam(entity.getDescription()), "description","chef.element.error.spam");
		}
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type",
				"draft");
	}

	@Override
	public void create(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
