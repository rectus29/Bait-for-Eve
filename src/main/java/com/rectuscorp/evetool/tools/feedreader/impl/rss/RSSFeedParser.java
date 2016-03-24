package com.rectuscorp.evetool.tools.feedreader.impl.rss;

import com.rectuscorp.evetool.tools.feedreader.IFeed;
import com.rectuscorp.evetool.tools.feedreader.IFeedParser;
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
 * The type Rss atom 2.0 feed parser.
 */
public class RSSFeedParser implements IFeedParser {

	private static final Logger log = LogManager.getLogger(RSSFeedParser.class);

	public IFeed parse(String response) {
		Feed out = new Feed();
		try {
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(response));
			//set feedtitle
			String feedTitle = document.selectSingleNode("//channel/title").getText();
			out.setName((feedTitle != null) ? feedTitle : null);
			for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//channel/item")) {
				FeedNode smfNode = new FeedNode();
				smfNode.setAuthor((charElement.selectSingleNode("source") != null) ? charElement.selectSingleNode("source").getText() : null);
				smfNode.setContent((charElement.selectSingleNode("description") != null) ? charElement.selectSingleNode("description").getText() : null);
				smfNode.setCreated((charElement.selectSingleNode("pubDate") != null) ? charElement.selectSingleNode("pubDate").getText() : null);
				smfNode.setLink((charElement.selectSingleNode("link") != null) ? charElement.selectSingleNode("link").getText() : null);
				smfNode.setSubject((charElement.selectSingleNode("title") != null) ? charElement.selectSingleNode("title").getText() : null);
				smfNode.setEnclosure((charElement.selectSingleNode("enclosure") != null) ? ((DOMElement)charElement.selectSingleNode("enclosure")).getAttribute("url") : null);
				out.getFeedNodeList().add(smfNode);
			}
		} catch (DocumentException e) {
			log.error("Error while SMF parsing", e);
		}
		return out;
	}
}
