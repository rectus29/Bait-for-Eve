package com.rectuscorp.evetool.web.page.market;

import com.rectuscorp.evetool.entities.crest.MarketGroup;
import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

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

	public MarketPage() {
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<MarketGroup> rootMarketGroup = serviceMarketGroup.getAllRootMarketGroup();



	}

	@Override
	public List<MenuElement> getMenuContribution() {
		List<MenuElement> out = new ArrayList<>();
		/*out.add(new MenuElement() {
			@Override
			public Link getLink() {
				return AjaxLink();
			}
		})*/
		return out;
	}
}
