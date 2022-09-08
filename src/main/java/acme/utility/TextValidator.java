package acme.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfiguration.SystemConfiguration;
import main.SpamManagement;

@Service
public class TextValidator {

	@Autowired
	protected SystemConfigurationRepository repository;


	public boolean spamChecker(final String text) {
		final SystemConfiguration sysConfig = this.repository.findSystemConfiguration();
		final Map<String, Double> terms = this.termStringToMap(sysConfig.getSpamTerms());
		return SpamManagement.checkSpam(text, terms, sysConfig.getSpamThreshold());
	}

	private Map<String, Double> termStringToMap(final String terms) {
		final HashMap<String, Double> result = new HashMap<>();

		final String[] parsedTerms = terms.replace("(", "").replace(")", "").split(",");

		for (int i = 0; i < parsedTerms.length - 2; i += 2) {
			result.put(parsedTerms[i], Double.parseDouble(parsedTerms[i+1]));
		}

		return result;
	}

}
