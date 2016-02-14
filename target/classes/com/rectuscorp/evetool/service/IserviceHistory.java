package com.rectuscorp.evetool.service;

import com.rectuscorp.evetool.entities.core.History;
import com.rectuscorp.evetool.entities.core.User;

import java.util.Date;
import java.util.List;

/**
 * User: ak4t0sh
 * Date: 4 févr. 2011
 * Time: 11:44:48
 */
public interface IserviceHistory extends  GenericManager<History, Long> {
    public List<History> getHistories(Date d, User u, String a, Long i, String t);
    public List getHistories(int first, int count, String sortProperty, boolean sortAsc);
    public History getLastHistory(User u, String a, Long i, String t);
}
