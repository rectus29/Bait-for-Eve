package com.rectuscorp.evetool.entities; /**
 *
 * User: olivier
 * Date: 24/07/11
 * Time: 07:47
 */


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class GenericEntity implements DomainObject, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date created = new Date();
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated = new Date();

	public Long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
