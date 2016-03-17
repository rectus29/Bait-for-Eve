package com.rectuscorp.evetool.tools.feedreader.impl.smf;

import com.rectuscorp.evetool.tools.feedreader.IFeed;
import com.rectuscorp.evetool.tools.feedreader.IFeedParser;
import com.rectuscorp.evetool.tools.feedreader.IFeedNode;
import com.rectuscorp.evetool.tools.feedreader.impl.Feed;
import com.rectuscorp.evetool.tools.feedreader.impl.FeedNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
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

/**
 * The type Smf feed parser.
 */
public class SMFFeedParser implements IFeedParser {

    private static final Logger log = LogManager.getLogger(SMFFeedParser.class);

    public IFeed parse(String response) {
        Feed out = new Feed();
        try {
            SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			//strip faild dtd
			response = response.replace("<smf:xml-feed xmlns:smf=\"http://www.simplemachines.org/\" xmlns=\"http://www.simplemachines.org/xml/recent\" xml:lang=\"fr-FR.utf8\">","<smf>");
			response = response.replace("</smf:xml-feed>","</smf>");
            DOMDocument document = (DOMDocument) saxReader.read(new StringReader(response));
            for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//recent-post ")) {
                FeedNode smfNode = new FeedNode();
                smfNode.setAuthor((charElement.selectSingleNode("poster")!= null)? charElement.selectSingleNode("poster/name").getText():null);
                smfNode.setContent((charElement.selectSingleNode("body")!= null)? charElement.selectSingleNode("body").getText():null);
                smfNode.setCreated((charElement.selectSingleNode("time")!= null)? charElement.selectSingleNode("time").getText():null);
                smfNode.setLink((charElement.selectSingleNode("link")!= null)? charElement.selectSingleNode("link").getText():null);
				smfNode.setSubject((charElement.selectSingleNode("subject")!= null)? charElement.selectSingleNode("subject").getText():null);
				out.getFeedNodeList().add(smfNode);
            }
        } catch (DocumentException e) {
            log.error("Error while SMF parsing", e);
        }
        return out;
    }
}
