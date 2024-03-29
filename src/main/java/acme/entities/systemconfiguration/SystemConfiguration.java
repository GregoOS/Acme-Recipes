package acme.entities.systemconfiguration;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	protected static final long serialVersionUID = 1L;

	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$", message = "{acme.validation.system-configuration.currency}")
	protected String currency;

	@NotBlank
	@Pattern(regexp = "^([A-Z]{3})(,[A-Z]{3})*$", message = "{acme.validation.system-configuration.accepted-currencies}")
	protected String acceptedCurrencies;

	@NotBlank
	@Pattern(regexp = "^(\\([\\w\\s\\d';áéíóúÁÉÍÓÚçÇ€$&@]*,\\d.\\d{1,2}\\),)*\\([\\w\\s\\d'áéíóúÁÉÍÓÚçÇ€$&@]*,\\d.\\d{1,2}\\)$", message = "{acme.validation.system-configuration.spam-terms}")
	protected String spamTerms;

	@PositiveOrZero
	@Max(1)
	protected double spamThreshold;

}
