package com.rectuscorp.evetool.entities.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event extends GenericEntity {

	private Date date;
	private String title;
	private int duration = 0;
	private Boolean importance;
	private String response;// enum
	private String text;
}
