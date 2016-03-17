package com.rectuscorp.evetool.tools.feedreader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;

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
 * The type Feed reader.
 */
public class FeedReader {

    private static final Logger log = LogManager.getLogger(FeedReader.class);
    private static FeedReader ourInstance;
    private static HttpClient client = new HttpClient();

	/**
	 * Singleton Getter
	 *
	 * @return FeedReader feed reader
	 */
	public static FeedReader get() {
        if (ourInstance == null)
            return new FeedReader();
        return ourInstance;
    }

	/**
	 * Read a feed and return node list
	 *
	 * @param url        URL to parse
	 * @param feedParser feedParser to use
	 * @return list of parsed list
	 */
	public IFeed read(URL url, IFeedParser feedParser){
        try {
            GetMethod get = new GetMethod(url.toString());
            client.executeMethod(get);
            String resp = get.getResponseBodyAsString();
            return feedParser.parse(resp);
        } catch (Exception e) {
            log.error("Error while feed reading", e);
        }

        return null;
    }
}
