package com.rectuscorp.evetool.api;

import com.google.gson.JsonArray;
import com.rectuscorp.evetool.entities.crest.*;
import com.rectuscorp.evetool.service.IserviceAttribute;
import com.rectuscorp.evetool.service.IserviceConstellation;
import com.rectuscorp.evetool.service.IserviceRegion;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONArray;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.SAXReader;

import javax.xml.ws.http.HTTPException;
import java.awt.print.Pageable;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
	public static String API_URL = "https://crest-tq.eveonline.com/";//http://crest-tq.eveonline.com/
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
			ourInstance = new EveCRESTApi();
			return ourInstance;
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
			GetMethod getDetail = new GetMethod(API_URL + "inventory/types/" + typeId + "/");
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

	/**
	 * retreive all market group from crest API
	 * @return List of group
	 */
	public List<MarketGroup> getAllMarketGroup(){
		log.debug("Crest get All marketGroup");
		List<MarketGroup> out = new ArrayList<>();
		try {
			URIBuilder url = new URIBuilder(API_URL + "market/groups/");
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String jsonResponse = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(jsonResponse);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("id")) {
					//add group child add here
					MarketGroup group = getMarketGroup(temp.getString("id_str"));
					if (group != null) {
						out.add(group);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while get group from CREST", e);
			out = new ArrayList<MarketGroup>();
		}
		return out;
	}

	public List<MarketGroup> getAllRootMarketGroup(){
		log.debug("Crest get All root marketGroup");
		List<MarketGroup> out = new ArrayList<>();
		try {
			URIBuilder url = new URIBuilder(API_URL + "market/groups/");
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String jsonResponse = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(jsonResponse);
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (temp.has("id") && !temp.has("parentGroup")) {
					//add group child add here
					MarketGroup group = getMarketGroup(temp.getString("id_str"));
					if (group != null) {
						out.add(group);
					}
				}
			}
		} catch (Exception e) {
			log.error("Error while get group from CREST", e);
			out = new ArrayList<>();
		}
		return out;
	}

	public List<Type> getMarketGroupContent(MarketGroup marketGroup){
		List<Type> out = new ArrayList<>();
		URIBuilder url = null;
		try {
			url = new URIBuilder(API_URL + "market/types/");
			url.addParameter("group", API_URL + "market/groups/" + marketGroup.getId() + "/");
			JSONArray jsonArray = getPagedItemsArray(url.build());
			jsonArray.length();


		} catch (Exception e) {
	 		log.error("Error while retreive market group content", e);
		}
		return out;
	}


	/**
	 *	retreive a market group from the crest api
	 */
	public MarketGroup getMarketGroup(String id){
		try {
			log.debug("Crest MarketGroup Import : " + id);
			GetMethod getDetail = new GetMethod(API_URL + "market/groups/" + id + "/");
			client.executeMethod(getDetail);
			JSONObject elJsonObj = new JSONObject(getDetail.getResponseBodyAsString());
			log.debug("Crest Import : " + elJsonObj.getString("name"));
			MarketGroup group = new MarketGroup();
			group.setId(elJsonObj.getLong("id"));
			group.setName(elJsonObj.getString("name"));
			group.setDescription(elJsonObj.getString("description"));
			if(elJsonObj.has("parentGroup")){
				group.setParentGroup(getMarketGroup(elJsonObj.getJSONObject("parentGroup").getString("id_str")));
			}
			if(elJsonObj.has("published"))
				group.setPublished(elJsonObj.getBoolean("published"));
			return group;
		} catch (Exception e) {
			log.error("Error while get type from CREST", e);
			return null;
		}

	}

	private JSONArray getPagedItemsArray(URI url) throws Exception{
		JSONArray out = new JSONArray();
		GetMethod getMethod = new GetMethod(url.toString());
		client.executeMethod(getMethod);
		JSONObject elJsonObj = new JSONObject(getMethod.getResponseBodyAsString());
		if(elJsonObj.has("items")){
			out.put(elJsonObj.getJSONArray("items"));
		}
		if(elJsonObj.has("pageCount")){
			URIBuilder uriBuilder = new URIBuilder(url);
			int pageCount = elJsonObj.getInt("pageCount");
			for(int i = 2; i<pageCount;i++){
				uriBuilder.setParameter("page", Integer.toString(i));
				//getMethod.setParams(new HttpMethodParams().setParameter("page", i));
				client.executeMethod(getMethod);
				elJsonObj = new JSONObject(getMethod.getResponseBodyAsString());
				JSONArray pageItemsArray = elJsonObj.getJSONArray("items");
				if(pageItemsArray != null){
					out.put(pageItemsArray);
				}
			}
		}
		return out;
	}

}

