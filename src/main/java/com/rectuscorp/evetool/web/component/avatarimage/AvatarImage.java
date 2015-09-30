package com.rectuscorp.evetool.web.component.avatarimage;


import com.rectuscorp.evetool.entities.Individual;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.Config;
import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(AvatarImage.class);

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;
    private IModel<String> context;
    private IModel<? extends Individual> model;

    public AvatarImage(String id) {
        super(id);
        /*this.model = new Model<Individual>() {
            @Override
            public Individual getObject() {
                return serviceUser.getCurrentUser();
            }
        };*/
    }

    public AvatarImage(String id, IModel<? extends Individual> model) {
        super(id, model);
        this.model = model;
    }

    public AvatarImage(String id, Individual model) {
        super(id);
        this.model = new Model<Individual>(model);
    }

    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "img");

        File file;
        /*if (model.getObject() instanceof Puppet) {
            file = new File(Config.get().getAvatarFolder() + File.separator + model.getObject().getId() + File.separator + model.getObject().getAvatarPath());
            if (model.getObject().getAvatarPath() != null && file.exists())
                tag.put("src", "/studentavatar/" + model.getObject().getId() + "/" + model.getObject().getAvatarPath());
            else
                tag.put("src", "/img/user.png");
        } else*/ if (model.getObject() instanceof User) {
            file = new File(Config.get().getAvatarFolder() + File.separator + model.getObject().getId() + File.separator + model.getObject().getAvatarPath());
            if (model.getObject().getAvatarPath() != null && file.exists())
                tag.put("src", "/avatar/" + model.getObject().getId() + "/" + model.getObject().getAvatarPath());
            else
                tag.put("src", "/img/user.png");
        } else {
            tag.put("src", "/img/user.png");
        }
    }
}