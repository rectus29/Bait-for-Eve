package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group")
public class Group extends GenericEntity {

	@Column(nullable =false)
	private String name;
	@Column(length = 65536)
	private String description;
	@Column
	private Boolean published = true;
	@OneToMany
	private List<Type> typeList = new ArrayList<Type>();
	@ManyToOne
	private Category categorie;
}
