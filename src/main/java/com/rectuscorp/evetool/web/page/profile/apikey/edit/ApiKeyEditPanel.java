package com.rectuscorp.evetool.web.page.profile.apikey.edit;

import com.rectuscorp.evetool.entities.core.ApiKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class ApiKeyEditPanel extends Panel {
	private static Logger log = LogManager.getLogger(ApiKeyEditPanel.class);
	private IModel<ApiKey> apiKeyIModel;

	public ApiKeyEditPanel(String id) {
		super(id);
	}

	public ApiKeyEditPanel(String id, IModel<?> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Form<ApiKey>("form", apiKeyIModel){
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new TextField<String>("keyId", new PropertyModel<String>(apiKeyIModel,"keyId")){

				});
			}
		});

	}
}
