package com.rectuscorp.evetool.web.security.error;

import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.page.base.BasePage;
import com.rectuscorp.evetool.web.page.home.HomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: olivier
 * Date: 22/04/11
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class ErrorPage extends BasePage {

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;

    private String name;
    private String email;
    private String issuefeedback;

    public ErrorPage() {

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        Random rnd = new Random();

        Form form = new Form("errorForm");
        add(form);

        User u = serviceUser.getCurrentUser();
        name = u.getUsername();
        email = u.getEmail();

        TextField nameTextfield = new TextField("name", new PropertyModel(this, "name"));
        form.add(nameTextfield);
        TextField mailTextfield = new TextField("email", new PropertyModel(this, "email"));
        form.add(mailTextfield);

        Label issuenumber = new Label("issuenumber", Model.of(rnd.nextInt(10000)));
        form.add(issuenumber);

        TextArea issuefeedbackTextarea = new TextArea("issuefeedback", new PropertyModel(this, "issuefeedback"));
        issuefeedbackTextarea.setRequired(true);
        form.add(issuefeedbackTextarea);

        AjaxSubmitLink submit = new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
               info("merci pour votre participation");
                target.add(feedbackPanel);
                //target.addComponent(feedbackPanel);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        };
        form.add(submit);
    }

    public Class getLinkPageClass(){
        return HomePage.class;
    }

    @Override
    public String getTitleContribution(){
        return "Erreur";
    }

}
