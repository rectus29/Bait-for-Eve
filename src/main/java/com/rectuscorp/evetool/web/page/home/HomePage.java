package com.rectuscorp.evetool.web.page.home;


/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for Andil         Date: 21/12/12 14:16 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import com.rectuscorp.evetool.entities.Invgroups;
import com.rectuscorp.evetool.entities.Invtypes;
import com.rectuscorp.evetool.service.IserviceInvgroups;
import com.rectuscorp.evetool.service.IserviceInvtypes;
import com.rectuscorp.evetool.service.*;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends ProtectedPage {


    @SpringBean
    IserviceUser serviceUser;

    @SpringBean
    IserviceInvgroups serviceInvgroups;

    @SpringBean
    IserviceInvtypes serviceInvtypes;

//    @SpringBean
//     IserviceInvtypesMateriels serviceInvtypesMateriels;




    public HomePage() {
    }

    public HomePage(IModel model) {
        super(model);
    }

    public HomePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
              Invtypes type = serviceInvtypes.get(32876l);



    }

    @Override
    public String getTitleContribution() {
        return "Accueil";
    }
}
