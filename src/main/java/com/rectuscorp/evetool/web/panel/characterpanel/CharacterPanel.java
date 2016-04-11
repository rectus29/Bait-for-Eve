package com.rectuscorp.evetool.web.panel.characterpanel;

import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.component.avatarimage.AvatarImage;
import org.apache.logging.log4j.LogManager;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class CharacterPanel extends Panel {
	private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(CharacterPanel.class);
	@SpringBean(name = "serviceUser")
	private IserviceUser serviceUser;
	private IModel<Character> characterIModel;
	private Character selectedChar;

	public CharacterPanel(String id) {
		super(id);
		characterIModel = new LoadableDetachableModel<Character>() {
			@Override
			protected Character load() {
				return serviceUser.getCurrentUser().getMainCharacter();
			}
		};
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new AvatarImage("avatarImg", characterIModel.getObject()));
		add(new DropDownChoice<Character>("avatarSelect", characterIModel, new LoadableDetachableModel<List<Character>>() {
			@Override
			protected List<Character> load() {
				return serviceUser.getCurrentUser().getCharacterList();
			}
		}, new ChoiceRenderer<Character>() {
			@Override
			public Object getDisplayValue(Character object) {
				return object.getFormatedName();
			}
		}).add(new AjaxFormComponentUpdatingBehavior("change") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		}));

	}
}
