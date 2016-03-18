package com.rectuscorp.evetool.web.panel.feeddisplaypanel;

import com.rectuscorp.evetool.tools.feedreader.FeedReader;
import com.rectuscorp.evetool.tools.feedreader.IFeed;
import com.rectuscorp.evetool.tools.feedreader.IFeedNode;
import com.rectuscorp.evetool.tools.feedreader.impl.rss.RSSFeedParser;
import com.rectuscorp.evetool.tools.feedreader.impl.smf.SMFFeedParser;
import com.rectuscorp.evetool.web.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.LoadableDetachableModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FeedDisplayPanel extends Panel {
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(FeedDisplayPanel.class);
	private URL url;
	private Class feedParserClass = RSSFeedParser.class;

	public FeedDisplayPanel(String id, String url) {
		super(id);
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			log.error("Error while url creation", e);
		}
	}

	public FeedDisplayPanel(String id, String url, Class feedParserClass ) {
		super(id);
		try {
			this.url = new URL(url);
			this.feedParserClass = feedParserClass;
		} catch (MalformedURLException e) {
			log.error("Error while url creation", e);
		}
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		LoadableDetachableModel<IFeed> feed = new LoadableDetachableModel<IFeed>() {
			@Override
			protected IFeed load() {
				return FeedReader.get().read(url, new SMFFeedParser());
			}
		};

		add(new Label("title", ((feed.getObject() != null)?feed.getObject().getName():"")));
		add(new ListView<IFeedNode>("lv", (feed.getObject() != null) ? feed.getObject().getFeedNodeList() : new ArrayList<IFeedNode>()) {
			@Override
			protected void populateItem(ListItem<IFeedNode> item) {
				item.add(new Label("date", item.getModelObject().getCreated()));
				item.add(new Label("author", item.getModelObject().getAuthor()));
				item.add(new Label("content", item.getModelObject().getContent()));
				item.add(new ExternalLink("link", item.getModelObject().getLink())
								.add(new Label("subject", item.getModelObject().getSubject()))
				);
			}
		});
	}
}
