package com.rectuscorp.evetool.web.security.signin;

import com.rectuscorp.evetool.web.component.BootStrapFeedbackPanel.BootStrapFeedbackPanel;
import com.rectuscorp.evetool.entities.History;
import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceHistory;
import com.rectuscorp.evetool.service.IserviceSession;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.page.base.BasePage;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Date;

/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class SigninPage extends BasePage {

    private static final Logger log = Logger.getLogger(SigninPage.class);

    @SpringBean(name = "serviceSession")
    private IserviceSession serviceSession;

    @SpringBean(name = "serviceUser")
    private IserviceUser serviceUser;

    @SpringBean(name = "serviceHistory")
    private IserviceHistory serviceHistory;

    private final class SignInForm extends StatelessForm {

        private String password;
        private String username;
        private Boolean rememberMe = false;

        public SignInForm(String id) {
            super(id);
            setModel(new CompoundPropertyModel(this));
            add(new TextField("username"));
            add(new PasswordTextField("password"));
        }

        @Override
        public final void onSubmit() {
            if (login(username, password, false)) {
                onSignInSucceeded();
            }
        }


    }

    public SigninPage() {
        add(new SignInForm("authenticationForm"));
        FeedbackPanel feed = new BootStrapFeedbackPanel("feedback");
        feed.setEscapeModelStrings(false);
        add(feed);
        setStatelessHint(true);
    }

    public SigninPage(PageParameters parameters) {
        add(new SignInForm("authenticationForm"));
        BootStrapFeedbackPanel feed = new BootStrapFeedbackPanel("feedback");
        feed.setEscapeModelStrings(false);
        add(feed);
        setStatelessHint(true);
    }


    /**
     * Sign in user if possible.
     *
     * @param username The username
     * @param password The password
     * @return True if signin was successful
     */
    public boolean login(String username, String password, boolean rememberMe) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            currentUser.login(token);
            serviceSession.addSubject(currentUser);
            return true;
        } catch (Exception ex) {
            error("Identifiant et/ou mot de passe invalide.");
        }
        return false;
    }


    protected void onSignInSucceeded() {
        User u = serviceUser.getCurrentUser();
        u.setLastLogin(new Date());
        serviceUser.save(u);
        serviceHistory.save(new History("login", "", ((ServletWebRequest) RequestCycle.get().getRequest()).getContainerRequest().getRemoteHost(), u.getId(), "user", u));

        // If login has been called because the user was not yet
        // logged in, than continue to the original destination,
        // otherwise to the Home page
        continueToOriginalDestination();
        setResponsePage(getApplication().getHomePage());
    }

    @Override
    public String getTitleContribution() {
        return "Connection";
    }
}
