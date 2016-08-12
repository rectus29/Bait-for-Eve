package com.rectuscorp.evetool.api;

import com.rectuscorp.evetool.entities.DecorableElement;
import com.rectuscorp.evetool.entities.MailingList;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.entities.core.Event;
import com.rectuscorp.evetool.entities.core.XmlApiKey;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.enums.State;
import com.rectuscorp.evetool.service.IserviceCorporation;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.spring.AppContext;
import com.rectuscorp.evetool.tools.FileUtils;
import com.rectuscorp.evetool.web.Config;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.util.file.Folder;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Eve xml api.
 */
public class EveXmlApi {

	private static final Logger log = LogManager.getLogger(EveXmlApi.class);
	/**
	 * The constant API_URL.
	 */
	private static String API_URL = "https://api.eveonline.com/";
	private static String IMG_SERV_URL = "https://image.eveonline.com/";
	private static EveXmlApi ourInstance;
	private static IserviceCorporation serviceCorporation;
	private static IserviceGeneric serviceGeneric;
	private HttpClient client = new HttpClient();

	/**
	 * Instantiates a new Eve xml api.
	 */
	public EveXmlApi() {
		ApplicationContext context = AppContext.getApplicationContext();
		serviceCorporation = (IserviceCorporation) context.getBean("serviceCorporation");
		serviceGeneric = (IserviceGeneric) context.getBean("serviceGeneric");
	}

	/**
	 * Singleton Getter
	 *
	 * @return EveXmlApi eve xml api
	 */
	public static EveXmlApi get() {
		if (ourInstance == null) {
			return new EveXmlApi();
		}
		return ourInstance;
	}

	/**
	 * Gets key information.
	 *
	 * @param xmlApiKey the xml api key
	 * @return the key information
	 */
	public XmlApiKey getKeyInformation(XmlApiKey xmlApiKey) {
		URIBuilder url = null;
		try {
			log.debug("Start get info for XMLAPIKEY " + xmlApiKey.getKeyId());
			url = new URIBuilder(API_URL + "/account/AccountStatus.xml.aspx");
			url.addParameter("keyID", xmlApiKey.getKeyId());
			url.addParameter("vCode", xmlApiKey.getVerificationCode());
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			if (document.selectSingleNode("//result/paidUntil") != null) {
				xmlApiKey.setPaidUntil(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(document.selectSingleNode("//result/paidUntil").getText())
				);
			}
			if (document.selectSingleNode("//result/createDate") != null) {
				xmlApiKey.setCreateDate(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(document.selectSingleNode("//result/createDate").getText())
				);
			}
			if (document.selectSingleNode("//result/logonCount") != null) {
				xmlApiKey.setLogonCount(Long.parseLong(document.selectSingleNode("//result/logonCount").getText()));
			}
			if (document.selectSingleNode("//result/logonMinutes") != null) {
				xmlApiKey.setLogonMinutes(Long.parseLong(document.selectSingleNode("//result/logonMinutes").getText()));
			}
			xmlApiKey.setState(State.ENABLE);
		} catch (Exception ex) {
			xmlApiKey.setState(State.ERROR);
			log.debug("Error while getting info for XMLAPIKEY " + xmlApiKey.getKeyId(), ex);
		}
		xmlApiKey = (XmlApiKey) serviceGeneric.save(xmlApiKey);
		try {
			log.debug("Start get character for XMLAPIKEY " + xmlApiKey.getKeyId());
			xmlApiKey.setCharacterList(getCharacterList(xmlApiKey));
		} catch (Exception ex) {
			xmlApiKey.setState(State.DISABLE);
			log.debug("Error while getting character for XMLAPIKEY " + xmlApiKey.getKeyId(), ex);
		}
		return xmlApiKey;
	}

	/**
	 * retreive characters for an xmlApiKey
	 *
	 * @param xmlApiKey the api key
	 * @return character list
	 */
	public List<Character> getCharacterList(XmlApiKey xmlApiKey) {
		List<Character> out = new ArrayList<Character>();
		try {
			URIBuilder url = null;
			url = new URIBuilder(API_URL + "account/Characters.xml.aspx");
			url.addParameter("keyID", xmlApiKey.getKeyId());
			url.addParameter("vCode", xmlApiKey.getVerificationCode());
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//row ")) {
				try {
					Character character = getCharacter(xmlApiKey, Long.parseLong(charElement.getAttribute("characterID")));
					out.add(character);
				} catch (Exception e) {
					log.error("Error while getCharacter from XMLAPI", e);
				}
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

	/**
	 * Get character character.
	 *
	 * @param xmlApiKey   the api key
	 * @param characterID the character id
	 * @return the character
	 * @throws Exception the exception
	 */
	public Character getCharacter(XmlApiKey xmlApiKey, Long characterID) throws Exception {
		try {
			if (xmlApiKey == null || characterID == null) {
				throw new Exception("apikey and character needed");
			}
			URIBuilder url = null;
			url = new URIBuilder(API_URL + "char/CharacterSheet.xml.aspx");
			url.addParameter("keyID", xmlApiKey.getKeyId());
			url.addParameter("vCode", xmlApiKey.getVerificationCode());
			url.addParameter("characterID", characterID.toString());
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			if (get.getStatusCode() != 200) {
				throw new Exception("Error while getting data from XMLAPI");
			}
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			Character character = new Character();
			character.setId(Long.parseLong(document.selectSingleNode("//result/characterID").getText()));
			character.setName(document.selectSingleNode("//result/name").getText());
			character.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(document.selectSingleNode("//result/DoB").getText()));
			if (document.selectSingleNode("//result/corporationID") != null) {
				Corporation corpo = serviceCorporation.get(Long.parseLong(document.selectSingleNode("//result/corporationID").getText()));
				character.setCorporation(corpo);
			}
			character.setXmlApiKey(xmlApiKey);
			character = (Character) serviceGeneric.save(character);
			//retreive image from server
			getImage(character);
			return character;
		} catch (Exception e) {
			log.error("Error while getCharacter from XMLAPI", e);
			throw e;
		}
	}

	/**
	 * get event for given character
	 *
	 * @param xmlApiKey   apikey to use
	 * @param characterID the character id
	 * @return list of event
	 * @throws Exception
	 */
	public List<Event> getEvent(XmlApiKey xmlApiKey, Character character) throws Exception {
		List<Event> out = new ArrayList<Event>();
		if (xmlApiKey == null || character == null) {
			throw new Exception("apikey and character needed");
		}
		URIBuilder url = null;
		url = new URIBuilder(API_URL + "char/UpcomingCalendarEvents.xml.aspx");
		url.addParameter("keyID", xmlApiKey.getKeyId());
		url.addParameter("vCode", xmlApiKey.getVerificationCode());
		url.addParameter("characterID", character.getId().toString());
		GetMethod get = new GetMethod(url.toString());
		client.executeMethod(get);
		if (get.getStatusCode() != 200) {
			throw new Exception("Error while getting data from XMLAPI");
		}
		String xmlResponse = get.getResponseBodyAsString();
		SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
		DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));

		for (Node tempNode : (List<Node>) document.selectNodes("//result/rowset/row")) {
			NamedNodeMap nodeMap = tempNode.getAttributes();
			Event event = new Event();
			event.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(nodeMap.getNamedItem("eventDate").getNodeValue()));
			event.setDuration(Integer.parseInt(nodeMap.getNamedItem("duration").getNodeValue()));
			event.setImportance("1".equals(nodeMap.getNamedItem("importance").getNodeValue()));
			event.setText(nodeMap.getNamedItem("eventText").getNodeValue());
			event.setTitle(nodeMap.getNamedItem("eventTitle").getNodeValue());
			event.setId(Long.parseLong(nodeMap.getNamedItem("eventID").getNodeValue()));
			Long ownerCode = Long.parseLong(nodeMap.getNamedItem("ownerID").getNodeValue());
//			serviceType.get
//			event.setOwner();
//			event.getCharacterList().add(character)
//			out .add((Event)serviceGeneric.save(event));
		}



		return out;
	}

	/**
	 * retreive free available corporation data from XML api
	 *
	 * @param corporationId id to request
	 * @return Corporation object
	 */
	public Corporation getCorporation(String corporationId) {
		return getCorporation(null, corporationId);
	}

	/**
	 * retreive corporation information
	 *
	 * @param xmlApiKey     apikey to use
	 * @param corporationID corporation id to get
	 * @return corporation object
	 */
	public Corporation getCorporation(XmlApiKey xmlApiKey, String corporationID) {
		Corporation out = null;
		try {
			URIBuilder url = new URIBuilder(API_URL + "corp/CorporationSheet.xml.aspx");
			if (xmlApiKey != null) {
				url.addParameter("keyID", xmlApiKey.getKeyId());
				url.addParameter("vCode", xmlApiKey.getVerificationCode());
			}
			url.addParameter("corporationID", corporationID);
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			if (document.selectSingleNode("//result") != null) {
				Corporation corp = new Corporation();
				if (document.selectSingleNode("//result/corporationID") != null) {
					corp.setId(Long.parseLong(document.selectSingleNode("//result/corporationID").getText()));
				}
				if (document.selectSingleNode("//result/corporationName") != null) {
					corp.setName(document.selectSingleNode("//result/corporationName").getText());
				}
				if (document.selectSingleNode("//result/ticker") != null) {
					corp.setTicker(document.selectSingleNode("//result/ticker").getText());
				}
				if (document.selectSingleNode("//result/description") != null) {
					corp.setDescription(document.selectSingleNode("//result/description").getText());
				}
				if (document.selectSingleNode("//result/url") != null) {
					corp.setUrl(document.selectSingleNode("//result/url").getText());
				}
				if (document.selectSingleNode("//result/taxRate") != null) {
					corp.setTaxRate(Double.parseDouble(document.selectSingleNode("//result/taxRate").getText()));
				}
				if (document.selectSingleNode("//result/memberCount") != null) {
					corp.setMemberCount(Integer.parseInt(document.selectSingleNode("//result/memberCount").getText()));
				}
				if (document.selectSingleNode("//result/shares") != null) {
					corp.setShares(Integer.parseInt(document.selectSingleNode("//result/memberCount").getText()));
				}
				out = corp;
				//retreive image from server
				getImage(out);
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

	/**
	 * Gets image.
	 *
	 * @param character the character
	 */
	public void getImage(DecorableElement character) {
		try {
			log.debug("get image from server for " + character.getId());
			String path = null;
			String filetype = null;
			Folder outputFolder = null;
			if (character instanceof Character) {
				path = "character";
				filetype = "jpg";
				outputFolder = Config.get().getCharacterFolder();
			} else if (character instanceof Corporation) {
				path = "corporation";
				filetype = "png";
				outputFolder = Config.get().getCorporationFolder();
			}
			URIBuilder url = new URIBuilder(IMG_SERV_URL + path + "/" + character.getId() + "_256." + filetype);
			GetMethod get = new GetMethod(url.toString());
			File outAvatarFile = new File(outputFolder + File.separator + character.getId() + "_256." + filetype);
			client.executeMethod(get);
			if (get.getStatusCode() != 404) {
				FileOutputStream outstream = new FileOutputStream(outAvatarFile);
				FileUtils.writeByteArrayToFile(outAvatarFile, get.getResponseBody());
				outstream.close();
			} else {
				log.warn("image not found on server");
			}
		} catch (Exception ex) {
			log.error("Error while retreive image from server", ex);
		}
	}


	/**
	 * Get server state.
	 *
	 * @return the map
	 */
	public Map<String, Object> getServerState(){
		HashMap<String, Object> out = new HashMap<>();
		try {
			URIBuilder url = new URIBuilder(API_URL + "/server/ServerStatus.xml.aspx");
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			if (document.selectSingleNode("//result") != null) {
				if (document.selectSingleNode("//result/serverOpen") != null) {
					out.put("serverOpen", document.selectSingleNode("//result/serverOpen").getText());
				}
				if (document.selectSingleNode("//result/onlinePlayers") != null) {
					out.put("onlinePlayers", document.selectSingleNode("//result/onlinePlayers").getText());
				}
			}
		} catch (Exception e) {
			log.error("Error while retreive server Status",e);
		}
		return out;
	}

	/**
	 * retreive mailingList of a character
	 * @param character
	 * @return
	 */
	public List<MailingList> getMailListFor(Character character){
		List<MailingList> out = new ArrayList<>();
		try {
			URIBuilder url = new URIBuilder(API_URL + "/char/mailinglists.xml.aspx");
			url.addParameter("characterID", character.getId().toString());
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			if (document.selectSingleNode("//result") != null) {
				for (Node tempNode : (List<Node>) document.selectNodes("//result/rowset/row")) {
					NamedNodeMap nodeMap = tempNode.getAttributes();
					MailingList mailingList = new MailingList();
					mailingList.setId(Long.parseLong(nodeMap.getNamedItem("listID").getNodeValue()));
					mailingList.setName(nodeMap.getNamedItem("displayName").getNodeValue());
					mailingList.getCharacterList().add(character);
					out.add(mailingList);
				}
			}
		} catch (Exception e) {
			log.error("Error while retreive server Status",e);
		}
		return out;
	}

}
