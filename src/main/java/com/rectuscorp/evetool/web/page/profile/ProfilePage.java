package com.rectuscorp.evetool.web.page.profile;

import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.page.profile.apikey.list.ApiKeyListPanel;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ProfilePage extends ProtectedPage {


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

        if (!getPageParameters().get(PANEL).isEmpty()) {
            if (APIKEY.equals(getPageParameters().get(PANEL).toString()))
                add((panel = new ApiKeyListPanel("panel")).setOutputMarkupId(true));
        } else
            add(panel = new EmptyPanel("panel"));

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
        return "Profile";
    }
}
