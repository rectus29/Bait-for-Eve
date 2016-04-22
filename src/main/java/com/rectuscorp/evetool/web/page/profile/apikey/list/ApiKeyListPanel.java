package com.rectuscorp.evetool.web.page.profile.apikey.list;

import com.rectuscorp.evetool.entities.core.*;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.enums.State;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.api.EveXmlApi;
import com.rectuscorp.evetool.web.component.andilmodal.EveModal;
import com.rectuscorp.evetool.web.component.confirmation.ConfirmationLink;
import com.rectuscorp.evetool.web.page.profile.apikey.edit.ApiKeyEditPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ApiKeyListPanel extends Panel {

    @SpringBean(name = "serviceUser")
    IserviceUser serviceUser;
    @SpringBean(name = "serviceGeneric")
    IserviceGeneric serviceGeneric;

    private EveModal modal;
    private WebMarkupContainer wmc;

    public ApiKeyListPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add((modal = new EveModal("modal")).setOutputMarkupId(true));
        add((wmc = new WebMarkupContainer("wmc"){
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new ListView<XmlApiKey>("lvKey", new LoadableDetachableModel<List<XmlApiKey>>() {
                    @Override
                    protected List<XmlApiKey> load() {
                        return serviceUser.getCurrentUser().getXmlApiKeyList();
                    }
                }) {
                    @Override
                    protected void populateItem(final ListItem<XmlApiKey> listItem) {
                        listItem.add(new Label("keyID", listItem.getModelObject().getKeyId()));
                        listItem.add(new Label("paidUntil", com.rectuscorp.evetool.web.Config.get().dateFormat(listItem.getModelObject().getPaidUntil())));
                        listItem.add(new Label("nbChar", listItem.getModelObject().getCharacterList().size()));
                        listItem.add(new EnumLabel<State>("state", listItem.getModelObject().getState()));
                        listItem.add(new AjaxLink("edit") {
                            @Override
                            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                                modal.setContent(new ApiKeyEditPanel(modal.getContentId(), listItem.getModel()) {
                                    @Override
                                    public void onFormSubmit(AjaxRequestTarget target, XmlApiKey xmlApiKey) {
                                        modal.close(target);
										target.add(wmc);
                                    }

                                    @Override
                                    public void onCancel(AjaxRequestTarget target) {
                                        modal.close(target);
                                    }
                                });
                                modal.show(ajaxRequestTarget);
                            }
                        });
                        listItem.add(new ConfirmationLink("remove", new ResourceModel("confirmation").getObject()) {
                            @Override
                            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                                //serviceGeneric.remove();
                            }
                        });
                    }
                });
            }
        }).setOutputMarkupId(true));

        add(new AjaxLink("add") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                modal.setContent(new ApiKeyEditPanel(modal.getContentId()) {
                    @Override
                    public void onFormSubmit(AjaxRequestTarget target, XmlApiKey xmlApiKey) {
                        target.add(wmc);
						List<Character> characterList =  EveXmlApi.get().getCharacterList(xmlApiKey);
						for(Character character: characterList)
							serviceGeneric.save(character);
						modal.close(target);
						target.add(wmc);
                    }

                    @Override
                    public void onCancel(AjaxRequestTarget target) {
                        modal.close(target);
                    }
                });
                modal.show(ajaxRequestTarget);
            }
        });
    }

}
