package com.rectuscorp.evetool.service.impl;

import com.rectuscorp.evetool.entities.User;
import com.rectuscorp.evetool.service.IserviceSession;
import com.rectuscorp.evetool.service.IserviceUser;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: olivier
 * Date: 9 févr. 2011
 */
@Service("serviceSession")
public class ServiceSession implements IserviceSession {

    private static final Logger log = LogManager.getLogger(ServiceSession.class);
    private IserviceUser serviceUser;

    Set<Subject> subjectList = new HashSet<Subject>();

    @Autowired
    public ServiceSession(IserviceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public void onStop(Session session) {
        Subject subjectToRemove = null;
        for (Subject subject : subjectList) {
            /*if (subject.getSession().getId().equals(com..mismastore.session.getId())) {
                subjectToRemove = subject;
                User u = serviceUser.getUser(subject);
                log.debug(u.getLastName() + " vient de deco");
                break;
            } */
        }
        if (subjectToRemove != null)
            subjectList.remove(subjectToRemove);
    }

    public void onExpiration(Session session) {
        Subject subjectToRemove = null;
        for (Subject subject : subjectList) {
            if (subject.getSession().getId().equals(session.getId())) {
                subjectToRemove = subject;
                User u = serviceUser.getUser(subject);
                log.debug(u.getUsername() + " vient d'expirer");
                break;
            }
        }
        if (subjectToRemove != null)
            subjectList.remove(subjectToRemove);
    }

    public List<Subject> getConnectedSubjets() {
        cleanList();
        return new ArrayList<Subject>(subjectList);
    }

    private void cleanList() {
        for (Subject subject : subjectList) {
            try {
                subject.getSession().getLastAccessTime();
            } catch (Exception e) {
                subjectList.remove(subject);
            }
        }
    }
}
