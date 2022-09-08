package acme.entities.memorandum;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.finedish.FineDish;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Memorandum extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}:[0-9]{4}$")
	protected String sequenceNumber;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationTime;

	@NotBlank
	@Length(max = 255)
	protected String report;

	@URL
	protected String link;

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected FineDish fineDish;

}

