package com.rectuscorp.evetool.web.security.maintenancepage;

import com.rectuscorp.evetool.service.IserviceConfig;
import com.rectuscorp.evetool.web.page.base.BasePage;
import org.apache.wicket.spring.injection.annot.SpringBean;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 27/12/14 22:19                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class MaintenancePage extends BasePage {

    @SpringBean(name = "serviceConfig")
    private IserviceConfig serviceConfig;

    public MaintenancePage() {
        super();
    }
}
