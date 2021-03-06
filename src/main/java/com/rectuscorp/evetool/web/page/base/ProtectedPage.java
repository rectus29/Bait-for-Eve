package com.rectuscorp.evetool.web.page.base;

import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.session.EveToolSession;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuContributionPanel;
import com.rectuscorp.evetool.web.panel.eveclockpanel.EveClockPanel;
import com.rectuscorp.evetool.web.panel.footerpanel.FooterPanel;
import com.rectuscorp.evetool.web.panel.menupanel.MenuPanel;
import com.rectuscorp.evetool.web.panel.serverstatepanel.ServerStatePanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;


@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated)
public class ProtectedPage extends BasePage {

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;

    public ProtectedPage() {
        super();

    }

    public ProtectedPage(IModel model) {
        super(model);

    }

    public ProtectedPage(PageParameters parameters) {
        super(parameters);

    }

    public EveToolSession getEveToolSession() {
        return (EveToolSession) getSession();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add((new MenuPanel("menuPanel")).setOutputMarkupId(true));
		add(new ServerStatePanel("serverState"));
		add(new EveClockPanel("clock"));
        add((new MenuContributionPanel("topPanel")).setOutputMarkupId(true));
//        add((new FooterPanel("footerPanel")).setOutputMarkupId(true));
    }

}
