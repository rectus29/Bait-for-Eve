package com.rectuscorp.evetool.web.page.admin;

/*-----------------------------------------------------*/
/* User: Rectus_29      Date: 03/03/2015 16:19 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectuscorp.evetool.web.page.admin.server.ServerAdminPanel;
import com.rectuscorp.evetool.web.page.admin.users.UserAdminPanel;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

//@ShiroSecurityConstraint(
//        constraint = ShiroConstraint.HasPermission, value = "admin:access"
//)
public class AdminPage extends ProtectedPage {

    public static String SERVER = "server";
    public static String USER = "user";
    public static String PLATFORM = "platform";
    public static String FACTOR = "factor";
    public static String PANEL = "panel";
    private Panel panel;

    public AdminPage() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        if(!getPageParameters().get(PANEL).isEmpty() && USER.equals(getPageParameters().get(PANEL).toString())){
            add(panel = new UserAdminPanel("panel"));
        }else
            add(panel = new ServerAdminPanel("panel"));

        add(new WebMarkupContainer("serverCont") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new BookmarkablePageLink<AdminPage>("serverLink", AdminPage.class, new PageParameters().add(PANEL, SERVER)));
                add(new AttributeAppender("class", (ServerAdminPanel.class.equals(panel.getClass()) ? "active" : "")));
            }
        });
        add(new WebMarkupContainer("userCont") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new BookmarkablePageLink<AdminPage>("userLink", AdminPage.class, new PageParameters().add(PANEL, USER)));
                add(new AttributeAppender("class", (UserAdminPanel.class.equals(panel.getClass()) ? "active" : "")));
            }
        });
    }

    @Override
    public String getTitleContribution() {
        return "Administration";
    }
}
