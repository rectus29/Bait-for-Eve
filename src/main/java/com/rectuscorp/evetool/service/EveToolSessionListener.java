package com.rectuscorp.evetool.service;

import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: olivier
 * Date: 7 févr. 2011
 */
@Component
public class EveToolSessionListener implements SessionListener {

    private static final Logger log = LogManager.getLogger(EveToolSessionListener.class);

    protected IserviceSession serviceSession;

    @Autowired
    public void setServiceSession(IserviceSession serviceSession) {
        this.serviceSession = serviceSession;
    }


    public void onStart(Session session) {
    }

    public void onStop(Session session) {
        serviceSession.onStop(session);
    }

    public void onExpiration(Session session) {
        serviceSession.onExpiration(session);
    }
}
