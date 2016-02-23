package com.rectuscorp.evetool.web.page.profile.apikey.list;

import com.rectuscorp.evetool.entities.core.ApiKey;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.component.confirmation.ConfirmationLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ApiKeyListPanel extends Panel {

	@SpringBean(name = "serviceUser")
	IserviceUser serviceUser;

	public ApiKeyListPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new ListView<ApiKey>("lvKey", new LoadableDetachableModel<List<ApiKey>>() {
			@Override
			protected List<ApiKey> load() {
				return serviceUser.getCurrentUser().getApiKeyList();
			}
		}) {
			@Override
			protected void populateItem(ListItem<ApiKey> listItem) {
				listItem.add(new Label("keyID", listItem.getModelObject().getKeyId()));
				listItem.add(new DateLabel("expirationDAte", null));
				listItem.add(new Label("nbChar", listItem.getModelObject().getCharacterList().size()));
				listItem.add(new AjaxLink("edit") {
					@Override
					public void onClick(AjaxRequestTarget ajaxRequestTarget) {

					}
				});
				listItem.add(new ConfirmationLink("remove", new ResourceModel("areyoushure").getObject()) {
					@Override
					public void onClick(AjaxRequestTarget ajaxRequestTarget) {

					}
				});
			}
		});
	}
}
