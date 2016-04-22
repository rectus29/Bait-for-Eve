package com.rectuscorp.evetool.web.page.crest;

import com.rectuscorp.evetool.entities.crest.*;
import com.rectuscorp.evetool.service.IserviceConstellation;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.service.IserviceRegion;
import com.rectuscorp.evetool.api.EveCRESTApi;
import com.rectuscorp.evetool.web.page.base.ProtectedPage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.spring.injection.annot.SpringBean;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __          | |             |__  / _      */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ / __| __| | | / __|   / / __, |    */
/*     | | \ \ __/ (__| |_| |_| __   / /_   / /     */
/*     |_|  |_|__|___|__|__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 27/12/14 22:19                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class CrestPage extends ProtectedPage {

	private static final Logger log = LogManager.getLogger(CrestPage.class);
	private JSONObject group;

	@SpringBean(name = "serviceGeneric")
	private IserviceGeneric serviceGeneric;
	@SpringBean(name = "serviceRegion")
	private IserviceRegion serviceRegion;
	@SpringBean(name = "serviceConstellation")
	private IserviceConstellation serviceConstellation;

	/**
	 * Instantiates a new Crest page.
	 */
	public CrestPage() {

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		//getRegions();
		//getConstellations();
		//getSolarSystems();
		//getAttributes();
		//getCategory();
		//getEffects();

	}

	private void getEffects() {
		HttpClient client = new HttpClient();
		try {

			for (int p = 1; p < 9; p++) {
				GetMethod get = new GetMethod(EveCRESTApi.API_URL + "dogma/effects/?page=" + p);
				client.executeMethod(get);
				String resp = get.getResponseBodyAsString();
				JSONObject jsonObj = new JSONObject(resp);
				for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
					JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
					if (temp.has("href")) {
						Effect effect = new Effect();
						effect.setId(temp.getLong("id"));
						effect.setName(temp.getString("name"));
						GetMethod getDetail = new GetMethod(temp.getString("href"));
						client.executeMethod(getDetail);
						JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
						if (elJsonObj.has("name")) {
							System.out.println(effect.getId() + " " + elJsonObj.getString("name"));
							effect.setName(elJsonObj.getString("name"));
							effect.setDisplayName(elJsonObj.getString("displayName"));
							effect.setDescription(elJsonObj.getString("description"));
							if (elJsonObj.has("postExpression"))
								effect.setPostExpression(elJsonObj.getLong("postExpression"));
							if (elJsonObj.has("preExpression"))
								effect.setPreExpression(elJsonObj.getLong("preExpression"));
							if (elJsonObj.has("isAssistance"))
								effect.setIsAssistance(elJsonObj.getBoolean("isAssistance"));
							if (elJsonObj.has("isOffensive"))
								effect.setIsOffensive(elJsonObj.getBoolean("isOffensive"));
							if (elJsonObj.has("disallowAutoRepeat"))
								effect.setDisallowAutoRepeat(elJsonObj.getBoolean("disallowAutoRepeat"));
							if (elJsonObj.has("isWarpSafe"))
								effect.setIsWarpSafe(elJsonObj.getBoolean("isWarpSafe"));
							if (elJsonObj.has("electronicChance"))
								effect.setElectronicChance(elJsonObj.getBoolean("electronicChance"));
							if (elJsonObj.has("rangeChance"))
								effect.setRangeChance(elJsonObj.getBoolean("rangeChance"));
							if (elJsonObj.has("effectCategory"))
								effect.setEffectCategory(elJsonObj.getLong("effectCategory"));
							if (elJsonObj.has("electronicChance"))
								effect.setElectronicChance(elJsonObj.getBoolean("electronicChance"));
							if (elJsonObj.has("published"))
								effect.setPublished(elJsonObj.getBoolean("published"));
						}
						serviceGeneric.save(effect);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCategory() {
		/*HttpClient client = new HttpClient();
        try {

            GetMethod get = new GetMethod(CrestObject.API_URL + "inventory/categories/");
            client.executeMethod(get);
            String resp = get.getResponseBodyAsString();
            JSONObject jsonObj = new JSONObject(resp);
            for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
                JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
                if (temp.has("href")) {
                    Category category = new Category();
                    category.setId(temp.getLong("id"));
                    GetMethod getDetail = new GetMethod(temp.getString("href"));
                    client.executeMethod(getDetail);
                    JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
                    category.setName(elJsonObj.getString("name"));
                    category.setPublished(elJsonObj.getBoolean("published"));
                    for (int j = 0; j < elJsonObj.getJSONArray("groups").length(); j++) {
                        Group group = new Group();
                        JSONObject groupJSONObject = (JSONObject) elJsonObj.getJSONArray("groups").get(j);
                        group.setName(groupJSONObject.getString("name"));
                        group.setDescription(groupJSONObject.getString("description"));
                        group.setPublished(groupJSONObject.getBoolean("published"));
                        for (int t = 0; t < elJsonObj.getJSONArray("types").length(); t++) {
                            JSONObject tempTypes = (JSONObject) elJsonObj.getJSONArray("types").get(t);
                            GetMethod getTypes = new GetMethod(tempTypes.getString("href"));
                            client.executeMethod(getTypes);
                            JSONObject typeJSONObject = new JSONObject(getTypes.getResponseBodyAsString());
                            //TODO FINISH
                            Type type = new Type();
                            type.setName();
                            type.setDescription();
                            type.setMass();
                            type.setPublished();
                            type.setCapacity();
                            type.setPortionSize();
                            type.setVolume();
                            type.setRadius();

                            type.getDogmaList().add(new Dogma());


                            group.getTypeList().add(type);
                        }
                        category.getGroupList().add(group);
                    }
                    serviceGeneric.save(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
	}

	private void getAttributes() {
		HttpClient client = new HttpClient();
		try {

			for (int p = 1; p < 5; p++) {
				GetMethod get = new GetMethod(EveCRESTApi.API_URL + "dogma/attributes/?page=" + p);
				client.executeMethod(get);
				String resp = get.getResponseBodyAsString();
				JSONObject jsonObj = new JSONObject(resp);
				for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
					JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
					if (temp.has("href")) {
						Attribute attribute = new Attribute();
						attribute.setId(temp.getLong("id"));
						GetMethod getDetail = new GetMethod(temp.getString("href"));
						client.executeMethod(getDetail);
						JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
						if (elJsonObj.has("name")) {
							System.out.println(attribute.getId() + " " + elJsonObj.getString("name"));
							attribute.setName(elJsonObj.getString("name"));
							attribute.setDisplayName(elJsonObj.getString("displayName"));
							attribute.setDescription(elJsonObj.getString("description"));
							if (elJsonObj.has("unit"))
								attribute.setUnit(elJsonObj.getLong("unit"));
							if (elJsonObj.has("highIsGood"))
								attribute.setHighIsGood(elJsonObj.getBoolean("highIsGood"));
							if (elJsonObj.has("stackable"))
								attribute.setStackable(elJsonObj.getBoolean("stackable"));
							if (elJsonObj.has("defaultValue"))
								attribute.setDefaultValue(elJsonObj.getLong("defaultValue"));
							if (elJsonObj.has("published"))
								attribute.setPublished(elJsonObj.getBoolean("published"));
							serviceGeneric.save(attribute);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
