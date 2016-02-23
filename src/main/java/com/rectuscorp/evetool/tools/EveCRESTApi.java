package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.crest.Constellation;
import com.rectuscorp.evetool.entities.crest.Position;
import com.rectuscorp.evetool.entities.crest.Region;
import com.rectuscorp.evetool.entities.crest.SolarSystem;
import com.rectuscorp.evetool.service.IserviceConstellation;
import com.rectuscorp.evetool.service.IserviceRegion;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public class EveCRESTApi {

	private static final Logger log = LogManager.getLogger(EveCRESTApi.class);
	public static String API_URL = "https://public-crest.eveonline.com/";
	public static String CLIENTID = "e0dbc9bc97cb46298f0f2f608057fd92";
	public static String SECRETKEY = "K5hidwrbGRcfcdH3EqyfY8ApHmwiKUuHsbEPvIKz";
	public static String CALLBACKURL = "http://alexandrebernard.fr/testcrest";
	public static String USER_AGENT = "eve-crest Java library - https://github.com/burberius/eve-crest - ";
	@SpringBean(name = "serviceRegion")
	private static IserviceRegion serviceRegion;
	@SpringBean(name = "serviceConstellation")
	private static IserviceConstellation serviceConstellation;
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
	 *
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
					if (tempRegion != null)
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
	 *
	 * @param regionID id to get
	 * @return region object
	 */
	private static Region getRegion(String regionID) {
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
	 *
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
				if (temp.has("id")) {
					Constellation constellation = getConstellation(temp.getString("id"));
					if (constellation != null)
						out.add(constellation);
				}
			}
		} catch (Exception e) {
			log.error("Error while constellation retrive from CREST", e);
		}
		return out;
	}

	/**
	 * return constellation from CREST
	 *
	 * @param constellationID id to retreive
	 * @return constellation object
	 */
	private static Constellation getConstellation(String constellationID) {
		Constellation constellation = null;
		try {
			GetMethod getDetail = new GetMethod(API_URL + "constellation/" + constellationID + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			constellation.setName(elJsonObj.getString("name"));
			constellation.setPosition(new Position((elJsonObj.getJSONObject("position")).getDouble("x"), (elJsonObj.getJSONObject("position")).getDouble("y"), (elJsonObj.getJSONObject("position")).getDouble("z")));
			Region region = serviceRegion.get(Long.parseLong(elJsonObj.getJSONObject("region").getString("href").split("/")[elJsonObj.getJSONObject("region").getString("href").split("/").length - 1]));
			constellation.setRegion(region);
		} catch (Exception e) {
			log.error("Error while constellation retrive from CREST", e);
		}
		return constellation;
	}

	private static List<SolarSystem> getSolarSystems() {
		List<SolarSystem> out = new ArrayList<SolarSystem>();
		try {
			GetMethod get = new GetMethod(API_URL + "solarsystems/");
			client.executeMethod(get);
			String resp = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(resp);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("id")) {
					SolarSystem solarSystem = getSolarSystem(temp.getString("id"));
					if (solarSystem != null)
						out.add(solarSystem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * get solar system data from CREST
	 *
	 * @param solarsystemId id to find
	 * @return solrsystem object
	 */
	private static SolarSystem getSolarSystem(String solarsystemId) {
		SolarSystem solarSystem = null;
		try {
			GetMethod getDetail = new GetMethod(API_URL + "solarsystems/" + solarsystemId + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			log.debug(elJsonObj.getString("name"));
			solarSystem = new SolarSystem();
			solarSystem.setName(elJsonObj.getString("name"));
			solarSystem.setSecurityStatus(elJsonObj.getDouble("securityStatus"));
			solarSystem.setSecurityClass(elJsonObj.getString("securityClass"));
			solarSystem.setPosition(new Position((elJsonObj.getJSONObject("position")).getDouble("x"), (elJsonObj.getJSONObject("position")).getDouble("y"), (elJsonObj.getJSONObject("position")).getDouble("z")));
			Constellation constellation = serviceConstellation.get(Long.parseLong(elJsonObj.getJSONObject("constellation").getString("href").split("/")[elJsonObj.getJSONObject("constellation").getString("href").split("/").length - 1]));
			solarSystem.setConstellation(constellation);
		} catch (Exception e) {
			log.error("Error while get solarsytem from CREST", e);
		}
		return solarSystem;

	}
}

