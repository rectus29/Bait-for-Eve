package com.rectuscorp.evetool.web.security.maintenancepage;

import com.rectuscorp.evetool.service.IserviceConfig;
import com.rectuscorp.evetool.web.page.base.BasePage;
import org.apache.wicket.spring.injection.annot.SpringBean;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for Andil         Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class MaintenancePage extends BasePage {

    @SpringBean(name = "serviceConfig")
    private IserviceConfig serviceConfig;

    public MaintenancePage() {
        super();
    }
}
