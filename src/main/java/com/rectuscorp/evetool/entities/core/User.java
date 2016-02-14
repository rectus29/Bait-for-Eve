package com.rectuscorp.evetool.entities.core;

import com.rectuscorp.evetool.enums.State;
import com.rectuscorp.evetool.enums.UserAuthentificationType;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User extends GenericEntity implements Individual {

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false, length = 65536)
	private String lastName;

	@Column(nullable = false, length = 65536)
	private String firstName;

	@Column(nullable = false)
	private String email;

	@Column
	private String avatarPath;

	@Column
	private State state = State.ENABLE;

	@Column
	private String salt = new SecureRandomNumberGenerator().nextBytes(64).toBase64();

	@ManyToOne(cascade = { CascadeType.PERSIST })
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
	private UserAuthentificationType userAuthentificationType = UserAuthentificationType.EMBED;

	public User() {
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
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

	public boolean hasRole(String roleName) {
		return role.getName().equals(roleName);
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

	public String getFormattedName() {
		return getFirstName() + " " + getLastName();
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public boolean isAdmin() {
		if (this.role != null)
			return this.role.getIsAdmin();
		return false;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public UserAuthentificationType getUserAuthentificationType() {
		return userAuthentificationType;
	}

	public void setUserAuthentificationType(UserAuthentificationType userAuthentificationType) {
		this.userAuthentificationType = userAuthentificationType;
	}
}
