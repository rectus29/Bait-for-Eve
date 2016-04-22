package com.rectuscorp.evetool.web.page.home;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 14:16 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.tools.feedreader.impl.smf.SMFFeedParser;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.feeddisplaypanel.FeedDisplayPanel;
import com.rectuscorp.evetool.web.panel.lazyloadPanel.LazyLoadPanel;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
		add(new LazyLoadPanel("feedReader") {
			@Override
			public Component getLazyLoadComponent(String markupId) {
				return new FeedDisplayPanel(markupId, "http://forum.federatis.fr/index.php?action=.xml", SMFFeedParser.class);
			}

		}.setOutputMarkupId(true));
		add(new LazyLoadPanel("feedReader2"){
			@Override
			public Component getLazyLoadComponent(String markupId) {
				return new FeedDisplayPanel(markupId, "https://www.themittani.com/feeds/all/rss.xml");
			}
		}.setOutputMarkupId(true));
		add(new LazyLoadPanel("feedReader3"){
			@Override
			public Component getLazyLoadComponent(String markupId) {
				return new FeedDisplayPanel(markupId, "https://newsfeed.eveonline.com/en-US/95/articles");
			}
		}.setOutputMarkupId(true));

		try {
			//EveXmlApi.get().getEvent(serviceUser.getCurrentUser().getXmlApiKeyList().get(0), serviceUser.getCurrentUser().getXmlApiKeyList().get(0).getCharacterList().get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getTitleContribution() {
		return "Accueil";
	}
}
