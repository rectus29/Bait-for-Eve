package com.rectuscorp.evetool.tools.feedreader;

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

import java.util.List;

/**
 * The interface Feed.
 */
public interface IFeed {

	/**
	 * Gets url.
	 *
	 * @return the url
	 */
	String getURL();

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets feed node list.
	 *
	 * @return the feed node list
	 */
	List<IFeedNode> getFeedNodeList();
}
