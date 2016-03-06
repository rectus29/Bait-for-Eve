package com.rectuscorp.evetool.entities.core;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "xmlapikey")
public class ApiKey extends GenericEntity {

    @Column(nullable = false)
    private String keyId;
    @Column(nullable = false, length = 65536)
    private String verificationCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apiKey")
    private List<Character> characterList = new ArrayList<Character>();
    @ManyToOne
    private User user;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
