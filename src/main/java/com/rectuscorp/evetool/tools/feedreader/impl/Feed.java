package com.rectuscorp.evetool.tools.feedreader.impl;

import com.rectuscorp.evetool.tools.feedreader.IFeed;
import com.rectuscorp.evetool.tools.feedreader.IFeedNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Feed.
 */
public class Feed implements IFeed {

	private String name;
	private List<IFeedNode> feedNodeList = new ArrayList<IFeedNode>();

	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<IFeedNode> getFeedNodeList() {
		return feedNodeList;
	}

	/**
	 * Sets feed node list.
	 *
	 * @param feedNodeList the feed node list
	 */
	public void setFeedNodeList(List<IFeedNode> feedNodeList) {
		this.feedNodeList = feedNodeList;
	}

	public String getURL() {
		return null;
	}
}
