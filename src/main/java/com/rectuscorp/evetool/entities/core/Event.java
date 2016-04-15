package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.entities.DecorableElement;
import com.rectuscorp.evetool.entities.crest.Alliance;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.enums.EventResponse;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event extends GenericEntity {

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(nullable = false)
	private String title;
	@Column
	private int duration = 0;
	@Column
	private Boolean importance = false;
	@Column
	private EventResponse response;
	@Column(length = 65536)
	private String text;
	@ManyToOne
	private Character character;
	@ManyToOne
	private Character characterOwner;
	@ManyToOne
	private Corporation corporationOwner;
	@ManyToOne
	private Alliance allianceOwner;

	public Event() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Boolean isImportance() {
		return importance;
	}

	public void setImportance(Boolean importance) {
		this.importance = importance;
	}

	public EventResponse getResponse() {
		return response;
	}

	public void setResponse(EventResponse response) {
		this.response = response;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public DecorableElement getOwner() {
		if (characterOwner != null) {
			return characterOwner;
		}
		if (corporationOwner != null) {
			return corporationOwner;
		}
		if (allianceOwner != null) {
			return allianceOwner;
		}
		return null;
	}

	public void setOwner(DecorableElement owner) {
		if (owner != null) {
			if (owner instanceof Character) {
				this.characterOwner = (Character)owner;
			}
			if (owner instanceof Corporation) {
				this.corporationOwner = (Corporation)owner;
			}
			if (owner instanceof Alliance) {
				this.allianceOwner = (Alliance)owner;
			}
		}
	}
}
