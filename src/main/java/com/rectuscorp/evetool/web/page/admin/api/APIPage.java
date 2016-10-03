package com.rectuscorp.evetool.web.page.admin.api;

import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;

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

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}

	@Override
	public List<MenuElement> getMenuContribution() {
		return null;
	}
}
