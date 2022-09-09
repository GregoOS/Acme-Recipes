package acme.entities.delor;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.element.Element;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delor extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp="^\\d{6}:\\d{2}[0,1]\\d[0,3]\\d$", message = "{delor.regex.code}")
	private String keylet;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date instantiationMoment;
	
	@NotBlank
	@Length(max=100)
	private String subject;
	
	@NotBlank
	@Length(max=255)
	private String explanation;
	
	@NotNull
	@Valid
	private Money income;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startPeriod;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishPeriod;
	
	@URL
	private String moreInfo;
	
//	Can be an ingredient or an utensil
	@OneToOne(optional=false) //one to one optional false as the relation is convulsory
	@Valid
	@NotNull
	protected Element element;

}
