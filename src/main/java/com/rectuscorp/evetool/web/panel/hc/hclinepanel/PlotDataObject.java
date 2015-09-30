package com.rectuscorp.evetool.web.panel.hc.hclinepanel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Rectus for Andil
 * Date: 03/09/12
 * Time: 15:09
 */
public class PlotDataObject implements Serializable {

    private String label;
    private Map<Date, Double> data;

    public PlotDataObject(Map<Date, Double> data, String label) {
        this.data = data;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<Date, Double> getData() {
        return data;
    }

    public void setData(HashMap<Date, Double> data) {
        this.data = data;
    }
}