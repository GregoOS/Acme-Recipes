package acme.features.chef.delor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.entities.element.Type;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.utility.TextValidator;

@Service
public class ChefDelorValidator {
	
	@Autowired
	protected ChefDelorRepository chefDelorRepository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository sysConfRepository;
	
	@Autowired
	protected TextValidator textValidator;
	
	public void validateDelor(final Request<Delor> request, final Delor entity, final Errors errors) {
		
		if(!errors.hasErrors("keylet")) {
//			Code example -> 123456:001122
			final String code = entity.getKeylet();
			final String[] splitter = code.split(":");
			final Integer yy = Integer.valueOf(splitter[1].substring(0,2));
			final Integer mm = Integer.valueOf(splitter[1].substring(2,4));
			final Integer dd = Integer.valueOf(splitter[1].substring(4));
			
			final Calendar calendar = new GregorianCalendar();

			calendar.setTime(entity.getInstantiationMoment());
			final Integer yyInstantiation = Calendar.getInstance().get(Calendar.YEAR)-2000;
			final Integer mmInstantiation = Calendar.getInstance().get(Calendar.MONTH) + 1;
			final Integer ddInstantiation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
			errors.state(request, yy.equals(yyInstantiation) && mm.equals(mmInstantiation) && dd.equals(ddInstantiation), "keylet", "chef.delor.form.error.keylet-not-correct");
		}
		
		if (!errors.hasErrors("subject")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getSubject()), "subject",
					"chef.delor.error.spam");
		}
		if (!errors.hasErrors("explanation")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getExplanation()), "explanation",
					"chef.delor.error.spam");
		}
		
		if(!errors.hasErrors("startPeriod")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getInstantiationMoment(), 1);
			
			errors.state(request, entity.getStartPeriod().after(minimumStartDate), "startPeriod", "chef.delor.form.error.startPeriod-too-close-to-creationPeriod");
		}
		
		if(!errors.hasErrors("finishPeriod")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartPeriod(), 1);
	
			errors.state(request, entity.getFinishPeriod().after(minimumFinishDate), "finishPeriod", "chef.delor.form.error.finishPeriod-too-close-to-startPeriod"); 
		}
		
		if (!errors.hasErrors("income")) {
			final Double amount=entity.getIncome().getAmount();
			final String currency=entity.getIncome().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "income", "chef.delor.form.error.negative-budget");
			errors.state(request, acceptedCurrencies.contains(currency), "income", "chef.delor.form.error.wrongCurrency");
		}
		
		
		if(!errors.hasErrors("elementId")) {
			errors.state(request, entity.getElement().getType().equals(Type.INGREDIENT), "elementId", "chef.delor.element.notcorrect"); 
		}
		
	}
	
}
