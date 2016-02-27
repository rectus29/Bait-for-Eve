package com.rectuscorp.evetool.entities.core;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: rectus_29
 * Date: 9 avr. 2010
 * Time: 15:30:53
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "config")
public class Config implements DomainObject {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ke", nullable = false, length = 65536)
    private String key;

    @Column(name = "val", length = 65536)
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (id != null ? !id.equals(config.id) : config.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
