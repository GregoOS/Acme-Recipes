package acme.entities.amount;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Amount extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@Min(1)
	protected int number;
	
	@NotBlank
	@Length(max = 10)
	protected String unit;

	// Derived attributes ----------------------------------------------------

	// Relationships ----------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Element element;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Recipe recipe;
}
