package com.rectuscorp.evetool.web.panel.menucontributionpanel;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 30/06/2016 12:11               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import org.apache.wicket.markup.html.link.Link;

/**
 * The type Menu element.
 */
public class MenuElement {
	private String text;
	private Link link;

	/**
	 * Instantiates a new Menu element.
	 *
	 * @param text the text
	 * @param link the link
	 */
	public MenuElement(String text, Link link) {
		this.text = text;
		this.link = link;
	}

	/**
	 * Gets text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets text.
	 *
	 * @param text the text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets link.
	 *
	 * @return the link
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * Sets link.
	 *
	 * @param link the link
	 */
	public void setLink(Link link) {
		this.link = link;
	}
}
