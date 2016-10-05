package com.rectuscorp.evetool.web.page.admin.api.panels.crestAPIPanel;

import com.rectuscorp.evetool.api.EveCRESTApi;
import com.rectuscorp.evetool.entities.crest.MarketGroup;
import com.rectuscorp.evetool.service.IserviceAttribute;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.service.impl.ServiceGeneric;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2016 17:54                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class CrestAPIPanel extends Panel {

	@SpringBean(name = "serviceGeneric")
	private static IserviceGeneric serviceGeneric;

	public CrestAPIPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new IndicatingAjaxLink("crestMarketGroup") {
			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				List<MarketGroup> marketGroupList = EveCRESTApi.get().getAllMarketGroup();
				for (MarketGroup temp : marketGroupList) {
					serviceGeneric.save(temp);
				}
			}
		});
	}
}
