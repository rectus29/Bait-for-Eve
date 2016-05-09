package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Dogma.
 */
@Entity
@Table(name = "dogma")
public class Dogma extends GenericEntity{

	@Column
	private long value = 0;
	@ManyToOne
	private Attribute attribute;
	@ManyToOne
	private Type type;

	public Dogma(long value, Attribute attribute, Type type) {
		this.value = value;
		this.attribute = attribute;
		this.type = type;
	}

	/**
	 * Gets value.
	 *
	 * @return the value
	 */
	public long getValue() {
		return value;
	}

	/**
	 * Sets value.
	 *
	 * @param value the value
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * Gets attribute.
	 *
	 * @return the attribute
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * Sets attribute.
	 *
	 * @param attribute the attribute
	 */
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
}
