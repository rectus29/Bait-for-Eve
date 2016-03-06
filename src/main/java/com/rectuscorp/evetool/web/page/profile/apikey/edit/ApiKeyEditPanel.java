package com.rectuscorp.evetool.web.page.profile.apikey.edit;

import com.rectuscorp.evetool.entities.core.ApiKey;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.tools.SecureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class ApiKeyEditPanel extends Panel {
	private static Logger log = LogManager.getLogger(ApiKeyEditPanel.class);

	@SpringBean(name = "serviceGeneric")
	IserviceGeneric serviceGeneric;
	@SpringBean(name = "serviceUser")
	private IserviceUser serviceUser;
	private ApiKey apiKey;


	public ApiKeyEditPanel(String id) {
		super(id);
		apiKey = new ApiKey();
	}

	public ApiKeyEditPanel(String id, IModel<ApiKey> model) {
		super(id, model);
		this.apiKey = model.getObject();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Form<ApiKey>("form"){
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new TextField<String>("keyId", new PropertyModel<String>(apiKey, "keyId")).setRequired(true));
				add(new TextField<String>("verificationCode", new PropertyModel<String>(apiKey,"verificationCode")).setRequired(true));
				add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						super.onSubmit(target, form);
						apiKey.setUser(serviceUser.getCurrentUser());
						apiKey = (ApiKey) serviceGeneric.save(apiKey);
						onFormSubmit(target, apiKey);
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {
						super.onError(target, form);
					}
				});
				add(new AjaxLink("cancel") {
					@Override
					public void onClick(AjaxRequestTarget ajaxRequestTarget) {
						onCancel(ajaxRequestTarget);
					}
				});
			}
		});
	}

	public abstract void onFormSubmit(AjaxRequestTarget target, ApiKey apiKey);

	public abstract void onCancel(AjaxRequestTarget target);
}
