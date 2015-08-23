package com.rectuscorp.evetool.entities; /**
 *
 * User: olivier
 * Date: 24/07/11
 * Time: 07:47
 */

import org.apache.log4j.Logger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GenericEntity implements DomainObject {

    private static final Logger log = Logger.getLogger(GenericEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
