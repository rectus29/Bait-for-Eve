package com.rectuscorp.evetool.web.page.market;

import com.rectuscorp.evetool.entities.crest.MarketGroup;
import com.rectuscorp.evetool.service.IserviceMarketGroup;
import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
/*                Date: 22/07/2016 10:46                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class MarketPage extends ProtectedPage implements IMenuContributor {

	public static final String PANEL = "panel";
	private static final Logger log = LogManager.getLogger(MarketPage.class);
	@SpringBean(name = "serviceMarketGroup")
	private IserviceMarketGroup serviceMarketGroup;

	public MarketPage() {
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<MarketGroup> rootMarketGroup = serviceMarketGroup.getAllRootMarketGroup();

		add(new ListView<MarketGroup>("rootMarketGroup", rootMarketGroup) {
			@Override
			protected void populateItem(ListItem<MarketGroup> item) {
				item.add(new Label("marketName", item.getModelObject().getName()));
			}
		});

	}

	@Override
	public List<MenuElement> getMenuContribution() {
		List<MenuElement> out = new ArrayList<>();
		out.add(new MenuElement(new ResourceModel("cart").getObject()) {
			@Override
			public Link getLink() {
				return new BookmarkablePageLink(getMenuElementMarkupID(), MarketPage.class, new PageParameters().add(PANEL, "CART"));
			}
		});
		return out;
	}
}
