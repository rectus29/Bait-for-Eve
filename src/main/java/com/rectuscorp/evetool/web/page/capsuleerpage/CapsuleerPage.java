package com.rectuscorp.evetool.web.page.capsuleerpage;

import com.rectuscorp.evetool.web.page.IMenuContributor;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import com.rectuscorp.evetool.web.panel.menucontributionpanel.MenuElement;
import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

import java.util.List;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 30/06/2016 11:28               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CapsuleerPage extends ProtectedPage implements IMenuContributor{

	public CapsuleerPage(IModel model) {
		super(model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

	}

	@Override
	public List<MenuElement> getMenuContribution() {

		return null;
	}
}
