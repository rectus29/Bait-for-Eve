package com.rectuscorp.evetool.tools.feedreader.impl.mrss;

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
 * The type MRss feed parser.
 */
public class MRSSFeedParser implements IFeedParser {

	private static final Logger log = LogManager.getLogger(MRSSFeedParser.class);

	public IFeed parse(String response) {
		Feed out = new Feed();
		try {
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(response));
			//set feedtitle
			String feedTitle = document.selectSingleNode("//feed/title").getText();
			out.setName((feedTitle != null) ? feedTitle : null);
			for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//feed/entry")) {
				FeedNode smfNode = new FeedNode();
				smfNode.setAuthor((charElement.selectSingleNode("//author/name") != null) ? charElement.selectSingleNode("//author/name").getText() : null);
				smfNode.setContent((charElement.selectSingleNode("content") != null) ? charElement.selectSingleNode("content").getText() : null);
				smfNode.setCreated((charElement.selectSingleNode("published") != null) ? ((DOMElement) charElement.selectSingleNode("published")).getText() : null);
				smfNode.setLink((((DOMElement)charElement.selectSingleNode("published")).getAttribute("href") != null) ? ((DOMElement)charElement.selectSingleNode("published")).getAttribute("href") : null);
				smfNode.setSubject((charElement.selectSingleNode("title") != null) ? charElement.selectSingleNode("title").getText() : null);
				//smfNode.setEnclosure((charElement.selectSingleNode("enclosure") != null) ? ((DOMElement)charElement.selectSingleNode("enclosure")).getAttribute("url") : null);
				out.getFeedNodeList().add(smfNode);
			}
		} catch (DocumentException e) {
			log.error("Error while SMF parsing", e);
		}
		return out;
	}
}
