package com.rectuscorp.evetool.web.page.home;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 14:16 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.tools.feedreader.impl.rss.RSSFeedParser;
import com.rectuscorp.evetool.tools.feedreader.impl.smf.SMFFeedParser;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.feeddisplaypanel.FeedDisplayPanel;
import com.rectuscorp.evetool.web.panel.lazyloadPanel.GigaLazyLoadPanel;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.net.URL;

public class HomePage extends ProtectedPage {

	@SpringBean(name = "serviceUser")
	IserviceUser serviceUser;

	public HomePage() {
	}



	public HomePage(IModel model) {
		super(model);
	}

	public HomePage(PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new GigaLazyLoadPanel("feedReader") {
			@Override
			public Component getLoadingComponent(String markupId) {
				return new FeedDisplayPanel(markupId, "http://forum.federatis.fr/index.php?action=.xml", SMFFeedParser.class);
			}
		});
		add(new GigaLazyLoadPanel("feedReader2"){
			@Override
			public Component getLoadingComponent(String markupId) {
				return new FeedDisplayPanel(markupId, "https://www.themittani.com/feeds/all/rss.xml");
			}
		});

	}

	@Override
	public String getTitleContribution() {
		return "Accueil";
	}
}
