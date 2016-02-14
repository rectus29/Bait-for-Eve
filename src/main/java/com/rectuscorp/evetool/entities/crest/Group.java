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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}
}
