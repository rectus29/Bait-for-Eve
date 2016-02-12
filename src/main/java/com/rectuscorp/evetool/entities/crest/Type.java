package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type extends GenericEntity{

	@Column(length = 65536)
	private String name;

	//TODO TO finnnish

}
