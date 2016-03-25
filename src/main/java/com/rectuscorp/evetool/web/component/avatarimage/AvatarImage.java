package com.rectuscorp.evetool.web.component.avatarimage;


import com.rectuscorp.evetool.entities.core.*;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.Config;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.File;


/*------------------------------------------------------*/
/* User: Rectus_29      Date: 06/03/12 18:28 			*/
/*                                                     	*/
/*                 All right reserved                  	*/
/*------------------------------------------------------*/

public class AvatarImage extends WebComponent {

    private static final Logger log = LogManager.getLogger(AvatarImage.class);

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;
    private IModel<String> context;
    private IModel<? extends Character> model;

    public AvatarImage(String id) {
        super(id);
        this.model = new Model<Character>() {
            @Override
            public Character getObject() {
                return serviceUser.getCurrentUser().getMainCharacter();
            }
        };
    }

    public AvatarImage(String id, IModel<? extends Character> model) {
        super(id, model);
        this.model = model;
    }

    public AvatarImage(String id, Character model) {
        super(id);
        this.model = new Model<Character>(model);
    }

    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "img");

        File file;
        if (model.getObject() instanceof Character) {
            file = new File(Config.get().getAvatarFolder() + File.separator + model.getObject().getId());
            if (model.getObject().getId() != null && file.exists())
                tag.put("src", "/avatar/" + model.getObject().getId() + "/"/* + model.getObject().getAvatarPath()*/);
            else
                tag.put("src", "/img/user.png");
        } else {
            tag.put("src", "/img/user.png");
        }
    }
}