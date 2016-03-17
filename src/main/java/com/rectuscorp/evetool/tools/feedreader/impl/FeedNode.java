package com.rectuscorp.evetool.tools.feedreader.impl;

import com.rectuscorp.evetool.tools.feedreader.IFeedNode;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 25/02/2016 16:59               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

/**
 * The default node.
 */
public class FeedNode implements IFeedNode {

    private String created;
    private String subject;
    private String content;
    private String link;
    private String author;

	/**
	 * Gets created.
	 *
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Sets created.
	 *
	 * @param created the created
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * Gets subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets subject.
	 *
	 * @param subject the subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets content.
	 *
	 * @param content the content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets link.
	 *
	 * @param link the link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets author.
	 *
	 * @param author the author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
}
