package com.rectuscorp.evetool.entities;

import com.rectuscorp.evetool.entities.core.*;
import com.rectuscorp.evetool.entities.core.Character;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 12/08/2016 10:34                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
@Table(name = "mailinglist")
public class MailingList extends GenericEntity {

	@Column
	private String name;

	@ManyToMany(targetEntity = Character.class)
	private List<Character> characterList = new ArrayList<>();

	public MailingList() {
	}

	public String getName() {
		return name;
	}

	public MailingList setName(String name) {
		this.name = name;
		return this;
	}

	public List<Character> getCharacterList() {
		return characterList;
	}

	public MailingList setCharacterList(List<Character> character) {
		this.characterList = character;
		return this;
	}
}
