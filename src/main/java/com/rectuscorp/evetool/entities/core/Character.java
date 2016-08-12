package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.entities.DecorableElement;
import com.rectuscorp.evetool.entities.MailingList;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.entities.crest.SolarSystem;
import com.rectuscorp.evetool.entities.crest.Type;
import com.rectuscorp.evetool.entities.mail.EveMail;
import com.rectuscorp.evetool.enums.EveRace;
import com.rectuscorp.evetool.enums.Gender;
import com.rectuscorp.evetool.enums.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "avatar")
public class Character extends GenericEntity implements DecorableElement {

	@Column(nullable = false)
	private String name;
	@Column
	private EveRace race = EveRace.GALLENTE;
	@Column
	private Gender gender = Gender.MALE;
	@Column
	private String factionID = "0";
	@Column
	private Integer freeSkillPoints = 0;
	@Column
	private Double balance = 0d;
	@Column
	private int intelligence = 0;
	@Column
	private int memory = 0;
	@Column
	private int charisma = 0;
	@Column
	private int perception = 0;
	@Column
	private int willpower = 0;
	@Column
	private String title;
	@Column
	private SolarSystem location;
	@Column
	private Type ship;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth = new Date();
	@ManyToOne
	private XmlApiKey apiKey;
	@ManyToOne
	private Corporation corporation;
	@ManyToMany(mappedBy = "characterList", cascade = CascadeType.PERSIST)
	private List<Event> eventList = new ArrayList<Event>();
	@ManyToMany(mappedBy = "characterList", cascade = CascadeType.PERSIST)
	private List<MailingList> mailingLists = new ArrayList<>();
	@ManyToMany
	private List<EveMail> eveMailList = new ArrayList<>();
	@Column
	private State state = State.ENABLE;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SolarSystem getLocation() {
		return location;
	}

	public void setLocation(SolarSystem location) {
		this.location = location;
	}

	public Type getShip() {
		return ship;
	}

	public void setShip(Type ship) {
		this.ship = ship;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date birthDay) {
		this.dateOfBirth = birthDay;
	}

	public XmlApiKey getXmlApiKey() {
		return apiKey;
	}

	public void setXmlApiKey(XmlApiKey xmlApiKey) {
		this.apiKey = xmlApiKey;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public String getFormatedName(){
		return getName();
	}

	public XmlApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(XmlApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
