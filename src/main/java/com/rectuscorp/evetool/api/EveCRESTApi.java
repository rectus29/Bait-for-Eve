package com.rectuscorp.evetool.api;

import com.rectuscorp.evetool.entities.crest.*;
import com.rectuscorp.evetool.service.IserviceAttribute;
import com.rectuscorp.evetool.service.IserviceConstellation;
import com.rectuscorp.evetool.service.IserviceRegion;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
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

/**
 * The type Eve crest api.
 */
public class EveCRESTApi {

	private static final Logger log = LogManager.getLogger(EveCRESTApi.class);
	/**
	 * The constant API_URL.
	 */
	public static String API_URL = "https://public-crest.eveonline.com/";//http://crest-tq.eveonline.com/
	@SpringBean(name = "serviceRegion")
	private static IserviceRegion serviceRegion;
	@SpringBean(name = "serviceConstellation")
	private static IserviceConstellation serviceConstellation;
	@SpringBean(name = "serviceAttribute")
	private static IserviceAttribute serviceAttribute;
	private static EveCRESTApi ourInstance;
	private static HttpClient client = new HttpClient();

	/**
	 * Singleton Getter
	 *
	 * @return EveCRESTApi eve crest api
	 */
	public static EveCRESTApi get() {
		if (ourInstance == null) {
			return new EveCRESTApi();
		}
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
					if (tempRegion != null) {
						out.add(tempRegion);
					}
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
	 * @return all constellations
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
					if (constellation != null) {
						out.add(constellation);
					}
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
			constellation = new Constellation();
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

	/**
	 * Gets solar systems.
	 *
	 * @return the solar systems
	 */
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
					if (solarSystem != null) {
						out.add(solarSystem);
					}
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
			log.debug("Crest Import : " + typeId);
			GetMethod getDetail = new GetMethod(API_URL + "types/" + typeId + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			log.debug(elJsonObj.getString("name"));
			type = new Type();
			type.setId(elJsonObj.getLong("id"));
			type.setName(elJsonObj.getString("name"));
			type.setCapacity(elJsonObj.getLong("capacity"));
			type.setDescription(elJsonObj.getString("description"));
			type.setPortionSize(elJsonObj.getLong("portionSize"));
			type.setIconID(elJsonObj.getLong("iconID"));
			type.setVolume(elJsonObj.getLong("volume"));
			type.setRadius(elJsonObj.getLong("radius"));
			type.setPublished(elJsonObj.getBoolean("published"));
			type.setMass(elJsonObj.getLong("mass"));
			//work on attributes
			for (int i = 0; i < elJsonObj.getJSONObject("dogma").getJSONArray("attributes").length(); i++) {
				JSONObject temp = (JSONObject) elJsonObj.getJSONObject("dogma").getJSONArray("attributes").get(i);
				if (temp != null) {
					Long attrId = temp.getJSONObject("attribute").getLong("id");
					Attribute attribute =getAttribute(attrId.toString());
					if (attribute != null) {
						long value = temp.getLong("value");
						type.getAttributesList().add(new Dogma(value, attribute, type));
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while get type from CREST", e);
		}
		return type;
	}

	/**
	 * Gets attribute.
	 *
	 * @param attributeID the attribute id
	 * @return the attribute
	 */
	public Attribute getAttribute(String attributeID) {
		try {
			log.debug("Crest Import : " + attributeID);
			GetMethod getDetail = new GetMethod(API_URL + "dogma/attributes/" + attributeID + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			log.debug("Crest Import : " + elJsonObj.getString("name"));
			Attribute attribute = new Attribute();
			attribute.setId(elJsonObj.getLong("id"));
			attribute.setName(elJsonObj.getString("name"));
			attribute.setDisplayName(elJsonObj.getString("displayName"));
			attribute.setDescription(elJsonObj.getString("description"));
			attribute.setUnit(elJsonObj.getLong("unitID"));
			attribute.setHighIsGood(elJsonObj.getBoolean("highIsGood"));
			attribute.setStackable(elJsonObj.getBoolean("stackable"));
			attribute.setDefaultValue(elJsonObj.getLong("defaultValue"));
			attribute.setPublished(elJsonObj.getBoolean("published"));
			return attribute;
		} catch (Exception e) {
			log.error("Error while get type from CREST", e);
			return null;
		}

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


	public List<Group> getAllMarketGroup(){
		List<Group> out = new ArrayList<Group>();
		try {
			URIBuilder url = new URIBuilder(API_URL + "/market/groups/");
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String jsonResponse = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(jsonResponse);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("id")) {
					Group group = getMarketGroup(temp.getString("id"));
					if (group != null) {
						out.add(group);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while get group from CREST", e);
			return null;
		}
		return out;
	}


	/**
	 *
	 */
	public Group getMarketGroup(String id){
		try {
			log.debug("Crest Import : " + id);
			GetMethod getDetail = new GetMethod(API_URL + "market/groups/" + id + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			log.debug("Crest Import : " + elJsonObj.getString("name"));
			Group group = new Group();
			group.setId(elJsonObj.getLong("id"));
			group.setName(elJsonObj.getString("name"));
			group.setDescription(elJsonObj.getString("description"));
			if(elJsonObj.has("parentGroup"))
				group.setParentGroup(getMarketGroup(elJsonObj.getJSONObject("parentGroup").getString("href")));
				group.setPublished(elJsonObj.getBoolean("published"));
			return group;
		} catch (Exception e) {
			log.error("Error while get type from CREST", e);
			return null;
		}

	}
}

