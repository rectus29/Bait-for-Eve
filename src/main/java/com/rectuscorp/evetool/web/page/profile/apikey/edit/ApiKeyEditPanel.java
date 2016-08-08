package com.rectuscorp.evetool.web.page.profile.apikey.edit;

import com.rectuscorp.evetool.entities.core.XmlApiKey;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.api.EveXmlApi;
import com.rectuscorp.evetool.web.component.BootStrapFeedbackPanel.BootStrapFeedbackPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public abstract class ApiKeyEditPanel extends Panel {
	private static Logger log = LogManager.getLogger(ApiKeyEditPanel.class);

	@SpringBean(name = "serviceGeneric")
	IserviceGeneric serviceGeneric;
	@SpringBean(name = "serviceUser")
	private IserviceUser serviceUser;
	private XmlApiKey xmlApiKey;
	private BootStrapFeedbackPanel feed;


	public ApiKeyEditPanel(String id) {
		super(id);
		xmlApiKey = new XmlApiKey();
	}

	public ApiKeyEditPanel(String id, IModel<XmlApiKey> model) {
		super(id, model);
		this.xmlApiKey = model.getObject();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Form<XmlApiKey>("form"){
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new TextField<String>("keyId", new PropertyModel<String>(xmlApiKey, "keyId")).add(new IValidator<String>() {
					public void validate(IValidatable<String> validatable) {
						if(validatable.getValue().length() != 7){
							ValidationError error = new ValidationError();
							error.setMessage("Key length error");
							validatable.error(error);
						}
					}
				}).setRequired(true));
				add(new TextField<String>("verificationCode", new PropertyModel<String>(xmlApiKey, "verificationCode")).add(new IValidator<String>() {
					public void validate(IValidatable<String> validatable) {
						if(validatable.getValue().length() != 64){
							ValidationError error = new ValidationError();
							error.setMessage("Verification Code length error");
							validatable.error(error);
						}
					}
				}).setRequired(true));
				add((feed= new BootStrapFeedbackPanel("feed")).setOutputMarkupId(true));
				add(new IndicatingAjaxLink("refresh") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						xmlApiKey = EveXmlApi.get().getKeyInformation(xmlApiKey);
						xmlApiKey = (XmlApiKey) serviceGeneric.save(xmlApiKey);
						info(new ResourceModel("refreshdone").getObject());
						target.add(feed);
					}

					@Override
					public boolean isEnabled() {
						return xmlApiKey.getUser()!= null;
					}
				});

				add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						super.onSubmit(target, form);
						xmlApiKey.setUser(serviceUser.getCurrentUser());
						//if creation import info
						if(xmlApiKey.getId() == null)
							xmlApiKey = EveXmlApi.get().getKeyInformation(xmlApiKey);
						xmlApiKey = (XmlApiKey) serviceGeneric.save(xmlApiKey);
						onFormSubmit(target, xmlApiKey);
					}

					@Override
					protected void onError(AjaxRequestTarget target, Form<?> form) {
						target.add(feed);
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

	public abstract void onFormSubmit(AjaxRequestTarget target, XmlApiKey xmlApiKey);

	public abstract void onCancel(AjaxRequestTarget target);
}
