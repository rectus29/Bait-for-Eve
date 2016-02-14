package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="corporation")
public class Corporation extends GenericEntity{

	@Column(length = 65536)
	private String name;
	@Column
	private Boolean isNPC = false;
	@OneToMany(mappedBy = "corporation")
	private List<Character> characterList = new ArrayList<Character>();
	@ManyToOne
	private Alliance alliance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isNPC() {
		return isNPC;
	}

	public void setIsNPC(Boolean isNPC) {
		this.isNPC = isNPC;
	}

	public List<Character> getCharacterList() {
		return characterList;
	}

	public void setCharacterList(List<Character> characterList) {
		this.characterList = characterList;
	}
}
