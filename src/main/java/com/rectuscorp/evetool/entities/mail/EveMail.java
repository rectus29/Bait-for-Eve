package com.rectuscorp.evetool.entities.mail;

import com.rectuscorp.evetool.entities.MailingList;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.entities.core.GenericEntity;
import com.rectuscorp.evetool.entities.crest.Alliance;
import com.rectuscorp.evetool.entities.crest.Corporation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 12/08/2016 14:21               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
@Table(name = "evemail")
public class EveMail extends GenericEntity implements IMail {

	@Temporal(TemporalType.TIMESTAMP)
	private Date sentDate;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String title;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	@ManyToOne(targetEntity = MailingList.class)
	private MailingList mailingList;
	@ManyToOne
	private Corporation corporation;
	@ManyToOne
	private Alliance alliance;
	@ManyToMany(mappedBy = "eveMailList")
	private List<Character> characterList = new ArrayList<>();

	public Date getSentDate() {
		return sentDate;
	}

	public EveMail setSentDate(Date sentDate) {
		this.sentDate = sentDate;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public EveMail setTitle(String title) {
		this.title = title;
		return this;
	}
}
