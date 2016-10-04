package com.rectuscorp.evetool.web.page.admin.api;

import com.rectuscorp.evetool.web.page.admin.api.panels.crestAPIPanel.CrestAPIPanel;
import com.rectuscorp.evetool.web.page.admin.api.panels.xmlApiPanel.XMLAPIPanel;
import com.rectuscorp.evetool.web.panel.serverstatepanel.ServerStatePanel;
import org.apache.wicket.markup.html.panel.Panel;

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
public class APIAdminPanel extends Panel {

	public APIAdminPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new ServerStatePanel("serverStatePanel"));
		add(new CrestAPIPanel("CrestPanel"));
		add(new XMLAPIPanel("XmlPanel"));
	}
}
