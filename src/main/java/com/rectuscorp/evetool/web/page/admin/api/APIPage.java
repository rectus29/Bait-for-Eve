package com.rectuscorp.evetool.web.page.admin.api;

import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.admin.api.panels.crestAPIPanel.CrestAPIPanel;
import com.rectuscorp.evetool.web.page.admin.api.panels.serverAPIStatePanel.ServerAPIStatePanel;
import com.rectuscorp.evetool.web.page.admin.api.panels.xmlApiPanel.XMLAPIPanel;
import com.rectuscorp.evetool.web.page.admin.server.ServerAdminPanel;
import com.rectuscorp.evetool.web.page.admin.users.UserAdminPanel;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
/*                Date: 03/10/2016 15:46                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class APIPage extends ProtectedPage implements IMenuContributor {

	public static String SERVER = "server";
	public static String CREST = "crest";
	public static String XML = "xml";
	public static String PANEL = "panel";

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (!getPageParameters().get(PANEL).isEmpty()) {
			if (CREST.equals(getPageParameters().get(PANEL).toString())) {
				add(new XMLAPIPanel("panel"));
			} else if (XML.equals(getPageParameters().get(PANEL).toString())) {
				add(new CrestAPIPanel("panel"));
			} else{
				add(new ServerAPIStatePanel("panel"));
			}
		}
	}

	@Override
	public String getTitleContribution() {
		return new ResourceModel("ApiGest").getObject();
	}

	@Override
	public List<MenuElement> getMenuContribution() {
		List<MenuElement> out = new ArrayList<>();
		out.add(new MenuElement(new ResourceModel("CrestAPI").getObject()) {
			@Override
			public Link getLink() {
				return new BookmarkablePageLink<APIPage>(
						getMenuElementMarkupID(),
						APIPage.class,
						new PageParameters().add(PANEL, CREST)
				);
			}
		});
		out.add(new MenuElement(new ResourceModel("XMLAPI").getObject()) {
			@Override
			public Link getLink() {
				return new BookmarkablePageLink<APIPage>(
						getMenuElementMarkupID(),
						APIPage.class,
						new PageParameters().add(PANEL, XML)
				);
			}
		});
		out.add(new MenuElement(new ResourceModel("server").getObject()) {
			@Override
			public Link getLink() {
				return new BookmarkablePageLink<APIPage>(
						getMenuElementMarkupID(),
						APIPage.class,
						new PageParameters().add(PANEL, SERVER)
				);
			}
		});
		return out;
	}
}
