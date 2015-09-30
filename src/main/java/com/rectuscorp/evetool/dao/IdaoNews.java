package com.rectuscorp.evetool.dao;


import com.rectuscorp.evetool.entities.News;

import java.util.Date;
import java.util.List;

/**
 * Created by Oliv'Generator.
 * User: Olivier
 * Date: 03 sept. 2012
 * Time: 05:32:27
 */
public interface IdaoNews extends GenericDao<News, Long> {

    public List<News> getEnableAndPublishNewsFor(String t, String Id, Date dateFin, Date dateDeb);
    public List<News> getEnableAndPublishNews(Integer nbNews);

}
