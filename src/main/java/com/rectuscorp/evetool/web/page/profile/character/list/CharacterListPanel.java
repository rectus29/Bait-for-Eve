package com.rectuscorp.evetool.web.page.profile.character.list;

import com.rectuscorp.evetool.entities.core.XmlApiKey;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.component.andilmodal.EveModal;
import com.rectuscorp.evetool.web.component.avatarimage.AvatarImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

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
	private WebMarkupContainer wmc;
	private LoadableDetachableModel<List<Character>> ldm;
	private PageableListView<Character> plv;
	private EveModal modal;
	private PagingNavigator navigator;

	public CharacterListPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		ldm = new LoadableDetachableModel<List<Character>>() {
			@Override
			protected List<Character> load() {
				List<Character> out = new ArrayList<Character>();
				for (XmlApiKey xmlApiKey : serviceUser.getCurrentUser().getXmlApiKeyList()) {
					out.addAll(xmlApiKey.getCharacterList());
				}
				return out;
			}
		};
		add((wmc = new WebMarkupContainer("wmc") {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(plv = new PageableListView<Character>("lv", ldm, 20) {
					@Override
					protected void populateItem(final ListItem<Character> item) {
						item.add(new WebMarkupContainer("default") {
							@Override
							public boolean isVisible() {
								return item.getModelObject().equals(serviceUser.getCurrentUser().getMainCharacter());
							}
						});
						item.add(new AvatarImage("avatar", item.getModelObject()));
						item.add(new Label("id", item.getModelObject().getId() + ""));
						item.add(new Label("name", item.getModelObject().getName()));
						item.add(new AvatarImage("corpAvatar", (item.getModelObject().getCorporation() != null) ? item.getModelObject().getCorporation() : null));
						item.add(new Label("corp", (item.getModelObject().getCorporation() != null) ? item.getModelObject().getCorporation().getName() : "-"));
						item.add(new AjaxLink("refresh") {
							@Override
							public void onClick(AjaxRequestTarget target) {
								modal.setTitle(new ResourceModel("refresh").getObject());
								modal.setContent(new EmptyPanel(modal.getContentId()));
								modal.show(target);
							}
						});
						item.add(new AjaxLink("makePrincipal") {
							@Override
							public void onClick(AjaxRequestTarget target) {
								serviceUser.getCurrentUser().setMainCharacter(item.getModelObject());
								serviceUser.save(serviceUser.getCurrentUser());
								target.add(wmc);
							}
						});
					}
				});

			}
		}).setOutputMarkupId(true));
		add((navigator = new PagingNavigator("navigator", plv) {
			@Override
			public boolean isVisible() {
				return ldm.getObject().size() > 20;
			}
		}).setOutputMarkupId(true));

		add((modal = new EveModal("modal")).setOutputMarkupId(true));
	}
}
