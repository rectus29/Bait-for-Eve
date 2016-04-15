package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.crest.*;
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

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 25/02/2016 16:59               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class EveCRESTApi {

	private static final Logger log = LogManager.getLogger(EveCRESTApi.class);
	public static String API_URL = "https://public-crest.eveonline.com/";
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
	public List<Region> getAllRegions() {
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
	public Region getRegion(String regionID) {
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
	public List<Constellation> getAllConstellations() {
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
	public Constellation getConstellation(String constellationID) {
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

	public List<SolarSystem> getSolarSystems() {
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
	public SolarSystem getSolarSystem(String solarsystemId) {
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


	/**
	 * get alliance data from CREST
	 *
	 * @param allianceId id to find
	 * @return allianceId object
	 */
	public Alliance getAlliance(String allianceId) {
		Alliance alliance = null;
		try {

		} catch (Exception e) {
			log.error("Error while get alliance from CREST", e);
		}
		return alliance;
	}

	/**
	 * get type data from CREST
	 *
	 * @param typeId id to find
	 * @return typeId object
	 */
	public Type getType(String typeId) {
		Type type = null;
		try {

		} catch (Exception e) {
			log.error("Error while get type from CREST", e);
		}
		return type;
	}


	/**
	 * retreive free available corporation data from XML api
	 *
	 * @param corporationId id to request
	 * @return Corporation object
	 */
	public Corporation getCorporation(String corporationId) {
		return EveXmlApi.get().getCorporation(null, corporationId);
	}
}

