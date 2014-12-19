package com.rectuscorp.evetool.web.page.base;

import com.rectuscorp.evetool.event.OnEvent;
import com.rectuscorp.evetool.event.RefreshEvent;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.session.EveToolSession;
import com.rectuscorp.evetool.web.panel.footerpanel.FooterPanel;
import com.rectuscorp.evetool.web.panel.toppanel.TopPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;


@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated)
public class ProtectedPage extends BasePage {

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;

    private Panel topPanel;

    public ProtectedPage() {
        super();

    }

    public ProtectedPage(IModel model) {
        super(model);

    }

    public ProtectedPage(PageParameters parameters) {
        super(parameters);

    }

    public EveToolSession getMismaSession() {
        return (EveToolSession) getSession();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add((topPanel = new TopPanel("topPanel")).setOutputMarkupId(true));
        add((new FooterPanel("footerPanel")).setOutputMarkupId(true));
    }


    @OnEvent
    public void onEvent(RefreshEvent event) {
        event.getTarget().add(topPanel);
    }
}
