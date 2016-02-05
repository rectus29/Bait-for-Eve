package com.rectuscorp.evetool.web.page.crest;

import com.rectuscorp.evetool.CrestObject;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __          | |             |__  / _      */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ / __| __| | | / __|   / / __, |    */
/*     | |    __/ (__| |_| |_| __   / /_   / /     */
/*     |_|  ____|___|__|__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 27/12/14 22:19                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class CrestPage extends ProtectedPage {

    private static final Logger log = LogManager.getLogger(CrestPage.class);
    private JSONObject group;

    public CrestPage() {

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        HttpClient client = new HttpClient();
        try {
            GetMethod get = new GetMethod( CrestObject.API_URL + "market/groups/");
            client.executeMethod(get);
            String resp = get.getResponseBodyAsString();
            JSONObject jsonObj = new JSONObject(resp);
            ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
                JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
                if (!temp.has("parentGroup"))
                    jsonObjects.add(temp);
            }
            Collections.sort(jsonObjects, new Comparator<JSONObject>() {
                public int compare(JSONObject o1, JSONObject o2) {
                    try {
                        return ((String) o1.get("name")).compareTo(((String) o2.get("name")));
                    } catch (Exception e) {
                        return 0;
                    }
                }
            });
            add(new DropDownChoice<JSONObject>("drop", new PropertyModel<JSONObject>(this, "group"),jsonObjects, new ChoiceRenderer<JSONObject>() {
                @Override
                public Object getDisplayValue(JSONObject object) {
                    try {
                        return object.get("name");
                    } catch (JSONException e) {
                        return object.toString();
                    }
                }

			}).add(new AjaxFormComponentUpdatingBehavior("change") {
                @Override
                protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                    log.debug("");
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
