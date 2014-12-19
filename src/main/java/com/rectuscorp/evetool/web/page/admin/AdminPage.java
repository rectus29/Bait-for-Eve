package com.rectuscorp.evetool.web.page.admin;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created with IntelliJ IDEA.
 * User: Rectus_29
 * Date: 08/05/13
 * Time: 14:27
 */
public class AdminPage extends WebPage {

    public AdminPage() {
    }

    public AdminPage(IModel<?> model) {
        super(model);
    }

    public AdminPage(PageParameters parameters) {
        super(parameters);
    }
}
