package com.rectuscorp.evetool.web.panel.toppanel;


/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for Andil         Date: 21/12/12 16:09 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import com.rectuscorp.evetool.enums.State;
import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.HashMap;
import java.util.List;

public class TopPanel extends Panel {
    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;


    private IModel<Integer> bookmarknumber, creditnumber, friendnumber, mailNumber;
    private WebMarkupContainer bookmark, wallet, friend, store, event, account, mailbox;

    public TopPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();






//        mailNumber = new LoadableDetachableModel<Integer>() {
//            @Override
//            protected Integer load() {
//                int out = 0;
//                List<Recipient> temp = serviceRecipient.getUnreadFor(serviceUser.getCurrentUser());
//                out = temp.size();
//                return out;
//            }
//        };
//
//
//
//        add((bookmark = new WebMarkupContainer("user")));
//        add((wallet = new WebMarkupContainer("shop")));
//        add((friend = new WebMarkupContainer("distrib")));
//        add((store = new WebMarkupContainer("store")));
////        add((event = new WebMarkupContainer("event")));
//        add((event = new WebMarkupContainer("promotion")));
////        add((store = new WebMarkupContainer("store")));
////        add((account = new WebMarkupContainer("account")));
////        add((mailbox = new WebMarkupContainer("mailbox")));
//
//        String url = RequestCycle.get().getRequest().getClientUrl().toString().split("/")[0];
//        if (url.equals("bookmark"))
//            bookmark.add(new AttributeAppender("class", " active"));
//        else if (url.equals("wallet"))
//            wallet.add(new AttributeAppender("class", " active"));
//        else if (url.equals("friend"))
//            friend.add(new AttributeAppender("class", " active"));
//        else if (url.equals("store"))
//            store.add(new AttributeAppender("class", " active"));
//        else if (url.equals("event"))
//            event.add(new AttributeAppender("class", " active"));
//        else if (url.equals("account"))
//            account.add(new AttributeAppender("class", " active"));
//        else if (url.equals("mailbox"))
//            mailbox.add(new AttributeAppender("class", " active"));
//
////        bookmark.add(new Label("bookmarknumber", bookmarknumber)).setOutputMarkupId(true);
////        wallet.add(new Label("creditnumber", creditnumber.getObject() + " €")).setOutputMarkupId(true);
////        friend.add(new Label("friendnumber", friendnumber)).setOutputMarkupId(true);
////        mailbox.add(new Label("mailNumber", mailNumber)).setOutputMarkupId(true);
//        add(new Label("name", serviceUser.getCurrentUser().getFormattedName()));
        this.setOutputMarkupId(true);
    }
}
