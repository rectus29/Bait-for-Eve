package com.rectuscorp.evetool.web.page.profile.character.list;

import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 28/02/2016                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class CharacterListPanel extends Panel {

	private static final Logger log = LogManager.getLogger(CharacterListPanel.class);
	@SpringBean(name = "serviceUser")
	private IserviceUser serviceUser;

    public CharacterListPanel(String id) {
        super(id);
    }

	@Override
	protected void onInitialize() {
		super.onInitialize();


	}
}
