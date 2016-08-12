package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.DecorableElement;
import com.rectuscorp.evetool.entities.core.*;
import com.rectuscorp.evetool.entities.core.Character;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="corporation")
public class Corporation extends GenericEntity implements DecorableElement{

	@Column(length = 65536)
	private String name;
	@Column(length = 65536)
	private String description;
	@Column(length = 65536)
	private String url;
	@Column
	private String ticker;
	@ManyToOne
	private Character CeoCharacter;
//	@ManyToOne
//	private Station baseOfOperation;
	@Column
	private Boolean isNPC = false;
	@OneToMany(mappedBy = "corporation")
	private List<Character> characterList = new ArrayList<Character>();
	@ManyToOne
	private Alliance alliance;
	@Column
	private Double taxRate = 0d;
	@Column
	private int shares = 0;
	@Column
	private int memberCount = 0;

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

	public Corporation setCharacterList(List<Character> characterList) {
		this.characterList = characterList;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Character getCeoCharacter() {
		return CeoCharacter;
	}

	public void setCeoCharacter(Character ceoCharacter) {
		CeoCharacter = ceoCharacter;
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getFormatedName() {
		return this.getName();
	}
}
