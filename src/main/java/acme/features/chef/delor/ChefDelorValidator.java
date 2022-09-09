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
//			Keylet example -> 123456:220909
			final String keylet = entity.getKeylet();
			final String[] splitter = keylet.split(":");
			final String keyletDate = splitter[1];
			
			final Calendar calendar = new GregorianCalendar();

			calendar.setTime(entity.getInstantiationMoment());
			final Integer yyInstantiation = Calendar.getInstance().get(Calendar.YEAR)-2000;
			final Integer mmInstantiation = Calendar.getInstance().get(Calendar.MONTH) + 1;
			final Integer ddInstantiation = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			final String yyInstantiationString = yyInstantiation.toString();
			final String mmInstantiationString = mmInstantiation.toString();
			final String ddInstantiationString = ddInstantiation.toString();
			final String yymmdd = yyInstantiationString.concat(mmInstantiationString).concat(ddInstantiationString);
			
			errors.state(request, keyletDate.equals(yymmdd), "keylet", "chef.delor.form.error.keylet-not-correct");
		}
		
		if (!errors.hasErrors("subject")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getSubject()), "subject",
					"chef.delor.error.spam");
		}
		if (!errors.hasErrors("explanation")) {
			errors.state(request, !this.textValidator.spamChecker(entity.getExplanation()), "explanation",
					"chef.delor.error.spam");
		}
		
		if(!errors.hasErrors("startDate")) {
			final Date minimumStartDate = DateUtils.addMonths(entity.getInstantiationMoment(), 1);
			
			errors.state(request, entity.getStartDate().after(minimumStartDate), "startDate", "chef.delor.form.error.startDate-too-close-to-creationDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			final Date minimumFinishDate = DateUtils.addWeeks(entity.getStartDate(), 1);
	
			errors.state(request, entity.getFinishDate().after(minimumFinishDate), "finishDate", "chef.delor.form.error.finishDate-too-close-to-startDate"); 
		}
		
		if (!errors.hasErrors("income")) {
			final Double amount=entity.getIncome().getAmount();
			final String currency=entity.getIncome().getCurrency();
			final String acceptedCurrencies=this.sysConfRepository.findSystemConfiguration().getAcceptedCurrencies();

			errors.state(request, amount>0., "income", "chef.delor.form.error.negative-income");
			errors.state(request, acceptedCurrencies.contains(currency), "income", "chef.delor.form.error.wrongCurrency");
		}
		
		
		if(!errors.hasErrors("elementId")) {
			errors.state(request, entity.getElement().getType().equals(Type.INGREDIENT), "elementId", "chef.delor.element.notcorrect"); 
		}
		
	}
	
}
