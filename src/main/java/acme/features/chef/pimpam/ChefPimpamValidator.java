package acme.features.chef.pimpam;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Type;
import acme.entities.pimpam.Pimpam;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class ChefPimpamValidator {
	
	@Autowired
	protected ChefPimpamRepository chefPimpamRepository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator textValidator;
	
	public void validatePimpam(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		
//		if(!errors.hasErrors("code")) {
////			Code example -> PIM-00-11-22
//			final String code = entity.getCode();
//			final String[] splitter = code.split("-");
//			final Integer yy = Integer.valueOf(splitter[1]);
//			final Integer mm = Integer.valueOf(splitter[2]);
//			final Integer dd = Integer.valueOf(splitter[3]);
//			
//			final Calendar calendar = new GregorianCalendar();
//
//			calendar.setTime(entity.getInstantiationMoment());
//			final Integer yyInstantiation = Calendar.getInstance().get(Calendar.YEAR)-2000;
//			final Integer mmInstantiation = Calendar.getInstance().get(Calendar.MONTH) + 1;
//			final Integer ddInstantiation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//			
//			errors.state(request, yy.equals(yyInstantiation) && mm.equals(mmInstantiation) && dd.equals(ddInstantiation), "code", "chef.pimpam.form.error.code-not-correct");
//		}
		
		if (!errors.hasErrors("title")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getTitle()), "title",
					"chef.pimpam.error.spam");
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getDescription()), "description",
					"chef.pimpam.error.spam");
		}
		
		if(!errors.hasErrors("startDate")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getInstantiationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "chef.pimpam.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartDate(), 1);
	
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "chef.pimpam.form.error.finishDate-too-close-to-startDate"); 
		}
		
		if (!errors.hasErrors("budget")) {
			final Double amount=entity.getBudget().getAmount();
			final String currency=entity.getBudget().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "budget", "chef.pimpam.form.error.negative-budget");
			errors.state(request, acceptedCurrencies.contains(currency), "budget", "chef.pimpam.form.error.wrongCurrency");
		}
		
		if(!errors.hasErrors("elementId")) {
			errors.state(request, entity.getElement().getType().equals(Type.UTENSIL), "elementId", "chef.pimpam.element.notcorrect"); 
		}
		
	}
	
}
