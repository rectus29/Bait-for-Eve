package com.rectuscorp.evetool.entities.crest;

import com.rectuscorp.evetool.entities.core.GenericEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*-----------------------------------------------------*/
/* User: Rectus_29                 Date: 21/04/13 16:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
@Entity
@Table(name = "marketgroups")
public class MarketGroup extends GenericEntity {

    @ManyToOne
    private MarketGroup parentGroupID;

    @OneToMany(mappedBy = "parentGroupID")
    private List<MarketGroup> childrenMarketGroup = new ArrayList<MarketGroup>();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

	public String getName() {
		return name;
	}

	public MarketGroup setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public MarketGroup setDescription(String description) {
		this.description = description;
		return this;
	}
}
