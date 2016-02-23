package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.crest.Constellation;
import com.rectuscorp.evetool.entities.crest.Position;
import com.rectuscorp.evetool.entities.crest.Region;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EveCRESTApi {

	private static final Logger log = LogManager.getLogger(EveCRESTApi.class);
	public static String API_URL = "https://public-crest.eveonline.com/";
	public static String CLIENTID = "e0dbc9bc97cb46298f0f2f608057fd92";
	public static String SECRETKEY = "K5hidwrbGRcfcdH3EqyfY8ApHmwiKUuHsbEPvIKz";
	public static String CALLBACKURL = "http://alexandrebernard.fr/testcrest";
	public static String USER_AGENT = "eve-crest Java library - https://github.com/burberius/eve-crest - ";
	private static EveCRESTApi ourInstance;
	private static HttpClient client = new HttpClient();


	/**
	 * Singleton Getter
	 *
	 * @return EveCRESTApi
	 */
	public static EveCRESTApi get() {
		if (ourInstance == null)
			return new EveCRESTApi();
		return ourInstance;
	}

	/**
	 * Get all region from crest api
	 * @return list region
	 */
	private static List<Region> getAllRegions() {
		List<Region> out = new ArrayList<Region>();
		try {
			GetMethod get = new GetMethod(API_URL + "regions/");
			client.executeMethod(get);
			String resp = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(resp);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("href")) {
					Region tempRegion = getRegion(temp.getString("id"));
					if(tempRegion != null)
						out.add(tempRegion);
				}
			}
		} catch (Exception e) {
			log.error("Error while CREST region getAll", e);
		}
		return out;
	}

	/**
	 * Get a region from CRESTAPI by given id
	 * @param regionID id to get
	 * @return region object
	 */
	private static Region getRegion(String regionID){
		Region tempRegion = null;
		try {
			GetMethod getDetail = new GetMethod(API_URL + "regions/" + regionID + "/");
			client.executeMethod(getDetail);
			JSONObject regionJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			tempRegion = new Region();
			tempRegion.setDescription(regionJsonObj.getString("description"));
			tempRegion.setName(regionJsonObj.getString("name"));
			tempRegion.setId(regionJsonObj.getLong("id"));
		} catch (Exception e) {
			log.error("Error while getREgion from CREST", e);
		}
		return tempRegion;
	}

	/**
	 * Get all constelations from CREST API
	 * @return
	 */
	private static List<Constellation> getAllConstellations() {
		List<Constellation> out = new ArrayList<Constellation>();
		try {
			GetMethod get = new GetMethod(API_URL + "constellations/");
			client.executeMethod(get);
			String resp = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(resp);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("href")) {
					Constellation constellation = new Constellation();
					constellation.setId(temp.getLong("id"));
					GetMethod getDetail = new GetMethod(temp.getString("href"));
					client.executeMethod(getDetail);
					JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
					log.debug(elJsonObj.getString("name"));
					constellation.setName(elJsonObj.getString("name"));
					constellation.setPosition(new Position((elJsonObj.getJSONObject("position")).getDouble("x"), (elJsonObj.getJSONObject("position")).getDouble("y"), (elJsonObj.getJSONObject("position")).getDouble("z")));
					Region region = serviceRegion.get(Long.parseLong(elJsonObj.getJSONObject("region").getString("href").split("/")[elJsonObj.getJSONObject("region").getString("href").split("/").length - 1]));
					constellation.setRegion(region);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

}
