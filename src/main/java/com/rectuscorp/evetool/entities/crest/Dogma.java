package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dogma")
public class Dogma extends GenericEntity{

	@Column
	private long value = 0;
	@ManyToOne
	private Attribute attribute;
	@ManyToOne
	private Type type;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
}
