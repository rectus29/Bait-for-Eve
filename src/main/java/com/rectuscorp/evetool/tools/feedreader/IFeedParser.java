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

import com.rectuscorp.evetool.tools.feedreader.impl.Feed;

/**
 * The interface Feed parser.
 */
public interface IFeedParser {

	/**
	 * Parse feed and return array list of node.
	 *
	 * @param response the response
	 * @return the array list
	 */
	IFeed parse(String response);
}
