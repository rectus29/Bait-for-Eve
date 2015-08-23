package com.rectuscorp.evetool.web;

import org.apache.log4j.Logger;
import org.apache.wicket.util.file.Folder;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

public class Config {

    private static final Logger log = Logger.getLogger(Config.class);
    private static Config ourInstance = new Config();
    public static final String RESOURCE_PATH = "files";

    private Folder uploadFolder = new Folder("uploads");

    private Folder rootFolder = null;
    private String dateFormat = "dd/MM/yyyy";
    private String fullDateFormat = dateFormat + " HH:mm:ss";
    private SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
    private DecimalFormat formatter = new DecimalFormat("###,###,###,###.###");
    private String defaultColor[] = {"#FFAA00", "#877F13", "#C4A64C", "#BF3E21", "#A61F2B", "#5C1644", "#BF6374", "#872858", "#541154", "#BDB738", "#E5C85B", "#0C83B4"};

    public Config() {

    }

    public static Config get() {
        return ourInstance;
    }


    public void set(String rootPath) {
        this.rootFolder = new Folder(rootPath);
        uploadFolder = new Folder(rootFolder + File.separator + "uploads");
        uploadFolder.mkdirs();
        dateFormat = "dd/MM/yyyy";
        fullDateFormat = dateFormat + " HH:mm:ss";
        dateFormater = new SimpleDateFormat(dateFormat);

    }

    public Folder getUploadFolder() {
        return uploadFolder;
    }

    public String getAbsoluteRessourcePath() {
        return "/" + RESOURCE_PATH;
    }

    public String getAbsoluteProductRessourcePath() {
        return "/" + RESOURCE_PATH + "/product";
    }

    public void setUploadFolder(Folder uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public Folder getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public Folder getResourceFolder() {
        return new Folder(rootFolder, File.separator + RESOURCE_PATH);
    }

    public Folder getProductResourceFolder() {
        return new Folder(rootFolder, File.separator + RESOURCE_PATH + File.separator + "product");
    }

    public String getDefaultColor() {
        String out = "";
        for (int i = 0; i < defaultColor.length; i++) {
            out += "'" + defaultColor[i] + "',";
        }
        return out.substring(0, out.length() - 1);
    }

    public String dateFormat(Date date) {
        return dateFormater.format(date);
    }

    public String dateHourFormat(Date date) {
        dateFormater.applyPattern(fullDateFormat);
        String out = dateFormater.format(date);
        dateFormater.applyPattern(dateFormat);
        return out;
    }

    public String dateFormat(java.sql.Date date) {
        return dateFormater.format(date);
    }

    public String dateFormat(String pattern, Date date) {
        dateFormater.applyPattern(pattern);
        String out = dateFormater.format(date);
        dateFormater.applyPattern(dateFormat);
        return out;
    }

    public String format(double number) {
        return formatter.format(number);
    }

    public String format(String format, Integer number) {
        formatter.applyPattern(format);
        String out = formatter.format(number);
        formatter.applyPattern(dateFormat);
        return out;
    }


}
