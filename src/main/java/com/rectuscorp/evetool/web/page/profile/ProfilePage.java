package com.rectuscorp.evetool.web.page.profile;

import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.page.profile.account.AccountPanel;
import com.rectuscorp.evetool.web.page.profile.apikey.list.ApiKeyListPanel;
import com.rectuscorp.evetool.web.page.profile.character.list.CharacterListPanel;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
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
/*                Date: 28/02/2016                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class ProfilePage extends ProtectedPage implements IMenuContributor {

	public static String PANEL = "panel";
	public static String APIKEY = "apikey";
	public static String CHARACTER = "character";
	public static String ACCOUNT = "account";
	private Panel panel;

	public ProfilePage() {
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		if (!getPageParameters().get(PANEL).isEmpty() && APIKEY.equals(getPageParameters().get(PANEL).toString())) {
			add((panel = new ApiKeyListPanel("panel")).setOutputMarkupId(true));
		} else if (!getPageParameters().get(PANEL).isEmpty() && CHARACTER.equals(getPageParameters().get(PANEL).toString())) {
			add((panel = new CharacterListPanel("panel")).setOutputMarkupId(true));
		} else {
			add(panel = new AccountPanel("panel"));
		}

		add(new WebMarkupContainer("character") {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new BookmarkablePageLink<ProfilePage>("characterLink", ProfilePage.class, new PageParameters().add(PANEL, CHARACTER)));
				add(new AttributeAppender("class", (ApiKeyListPanel.class.equals(panel.getClass()) ? "active" : "")));
			}
		});
		add(new WebMarkupContainer("account") {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new BookmarkablePageLink<ProfilePage>("accountLink", ProfilePage.class, new PageParameters().add(PANEL, ACCOUNT)));
				add(new AttributeAppender("class", (ApiKeyListPanel.class.equals(panel.getClass()) ? "active" : "")));
			}
		});
		add(new WebMarkupContainer("apiKey") {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new BookmarkablePageLink<ProfilePage>("apiKeyLink", ProfilePage.class, new PageParameters().add(PANEL, APIKEY)));
				add(new AttributeAppender("class", (ApiKeyListPanel.class.equals(panel.getClass()) ? "active" : "")));
			}
		});


	}

	@Override
	public String getTitleContribution() {
		return "Account management";
	}

	@Override
	public List<MenuElement> getMenuContribution() {
		List<MenuElement> out = new ArrayList<MenuElement>();
		out.add(new MenuElement("account",new BookmarkablePageLink<ProfilePage>("characterLink", ProfilePage.class, new PageParameters().add(PANEL, CHARACTER))));
		return out;
	}
}
