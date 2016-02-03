package com.rectuscorp.evetool.web.security.unauthorizedpage;

import com.rectuscorp.evetool.web.page.base.BasePage;
import com.rectuscorp.evetool.web.panel.footerpanel.FooterPanel;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class UnauthorizedPage extends BasePage {

    private static final Logger log = LogManager.getLogger(UnauthorizedPage.class);

    public UnauthorizedPage(){
    }

    public UnauthorizedPage(IModel model) {
        super(model);
    }

    public UnauthorizedPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add((new FooterPanel("footerPanel")).setOutputMarkupId(true));

    }
}
