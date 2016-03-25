package com.rectuscorp.evetool.entities.core;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Xml api key.
 */
@Entity
@Table(name = "xmlapikey")
public class XmlApiKey extends GenericEntity {

	@Column(nullable = false)
	private String keyId;
	@Column(nullable = false, length = 65536)
	private String verificationCode;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date paidUntil = new Date();
	@Column
	private long logonCount = 0;
	@Column
	private long logonMinutes = 0;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "apiKey")
	private List<Character> characterList = new ArrayList<Character>();
	@ManyToOne
	private User user;

	/**
	 * Gets key id.
	 *
	 * @return the key id
	 */
	public String getKeyId() {
		return keyId;
	}

	/**
	 * Sets key id.
	 *
	 * @param keyId the key id
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * Gets verification code.
	 *
	 * @return the verification code
	 */
	public String getVerificationCode() {
		return verificationCode;
	}

	/**
	 * Sets verification code.
	 *
	 * @param verificationCode the verification code
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	/**
	 * Gets create date.
	 *
	 * @return the create date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets create date.
	 *
	 * @param createDate the create date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets paid until.
	 *
	 * @return the paid until
	 */
	public Date getPaidUntil() {
		return paidUntil;
	}

	/**
	 * Sets paid until.
	 *
	 * @param paidUntil the paid until
	 */
	public void setPaidUntil(Date paidUntil) {
		this.paidUntil = paidUntil;
	}

	/**
	 * Gets logon count.
	 *
	 * @return the logon count
	 */
	public long getLogonCount() {
		return logonCount;
	}

	/**
	 * Sets logon count.
	 *
	 * @param logonCount the logon count
	 */
	public void setLogonCount(long logonCount) {
		this.logonCount = logonCount;
	}

	/**
	 * Gets logon minutes.
	 *
	 * @return the logon minutes
	 */
	public long getLogonMinutes() {
		return logonMinutes;
	}

	/**
	 * Sets logon minutes.
	 *
	 * @param logonMinutes the logon minutes
	 */
	public void setLogonMinutes(long logonMinutes) {
		this.logonMinutes = logonMinutes;
	}

	/**
	 * Gets character list.
	 *
	 * @return the character list
	 */
	public List<Character> getCharacterList() {
		return characterList;
	}

	/**
	 * Sets character list.
	 *
	 * @param characterList the character list
	 */
	public void setCharacterList(List<Character> characterList) {
		this.characterList = characterList;
	}

	/**
	 * Gets user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets user.
	 *
	 * @param user the user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "keyID=" + this.getKeyId() + "&vCode=" + this.getVerificationCode();
	}
}
