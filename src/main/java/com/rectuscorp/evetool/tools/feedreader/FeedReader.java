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

import com.rectuscorp.evetool.entities.crest.Region;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

public class FeedReader {

    private static final Logger log = LogManager.getLogger(FeedReader.class);
    private static FeedReader ourInstance;
    private static HttpClient client = new HttpClient();

    /**
     * Singleton Getter
     *
     * @return FeedReader
     */
    public static FeedReader get() {
        if (ourInstance == null)
            return new FeedReader();
        return ourInstance;
    }


    /**
     * Read a feed and return node list
     * @param url URL to parse
     * @param feedParser feedParser to use
     * @return list of parsed list
     */
    public ArrayList<INode> read(URL url, IFeedParser feedParser){
        ArrayList<INode> out = new ArrayList<INode>();
        try {
            GetMethod get = new GetMethod(url.toString());
            client.executeMethod(get);
            String resp = get.getResponseBodyAsString();
            out = feedParser.parse(resp);
        } catch (Exception e) {
            log.error("Error while CREST region getAll", e);
        }

        return out;
    }
}
