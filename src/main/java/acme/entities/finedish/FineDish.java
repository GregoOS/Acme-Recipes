package acme.entities.finedish;

import java.util.Date;

import javax.persistence.Column;
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

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	protected Status status;

	// AA:AAA-00  AAA-00
	@NotNull
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique = true)
	protected String code;

	@NotBlank
	@Length(max = 255)
	protected String request;

	@NotNull
	@Valid
	protected Money budget;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date endDate;

	@URL
	protected String link;

	protected boolean draft;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Chef chef;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Epicure epicure;

}
