package com.rectuscorp.evetool.web.panel.feeddisplaypanel;

import com.rectuscorp.evetool.tools.feedreader.IFeedNode;
import com.rectuscorp.evetool.tools.feedreader.impl.smf.SMFFeedParser;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

public class FeedDisplayPanel extends Panel {

	private String url;

	public FeedDisplayPanel(String id, String url) {
		super(id);
		this.url = url;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();


		add(new ListView<IFeedNode>("lv", new LoadableDetachableModel<List<IFeedNode>>() {
			@Override
			protected List<IFeedNode> load() {
				return new SMFFeedParser().parse(url);
			}
		}){
			@Override
			protected void populateItem(ListItem<IFeedNode> item) {
				item.add(new DateLabel("date",item.getModelObject().getCreated()));
				item.add(new DateLabel("date",item.getModelObject().getCreated()));
				item.add(new DateLabel("date",item.getModelObject().getCreated()));
				item.add(new DateLabel("date",item.getModelObject().getCreated()));
			}
		});
	}
}
