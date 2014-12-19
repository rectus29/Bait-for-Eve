package com.rectuscorp.evetool.entities;

import com.rectuscorp.evetool.enums.State;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User extends GenericEntity implements Serializable {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String email;

    @Column
    private State state = State.ENABLE;

    @Column
    private String salt = new SecureRandomNumberGenerator().nextBytes(64).toBase64();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Role role;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date restoreSessionDate;

    @Column(name = "restoreSession")
    private String restoreSession;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getRestoreSessionDate() {
        return restoreSessionDate;
    }

    public void setRestoreSessionDate(Date restoreSessionDate) {
        this.restoreSessionDate = restoreSessionDate;
    }

    public String getRestoreSession() {
        return restoreSession;
    }

    public void setRestoreSession(String restoreSession) {
        this.restoreSession = restoreSession;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
