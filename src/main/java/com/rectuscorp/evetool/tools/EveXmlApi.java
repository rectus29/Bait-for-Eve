package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.entities.core.XmlApiKey;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.enums.State;
import com.rectuscorp.evetool.service.IserviceCorporation;
import com.rectuscorp.evetool.service.IserviceGeneric;
import com.rectuscorp.evetool.spring.AppContext;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The type Eve xml api.
 */
public class EveXmlApi {

	private static final Logger log = LogManager.getLogger(EveXmlApi.class);
	/**
	 * The constant API_URL.
	 */
	public static String API_URL = "https://api.eveonline.com/";
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
		}catch(Exception ex){
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
	 * retreive character for an xmlApiKey
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
				}catch (Exception e){
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
	 */
	public Character getCharacter(XmlApiKey xmlApiKey, Long characterID) throws Exception{
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
			return (Character) serviceGeneric.save(character);
		} catch (Exception e) {
			log.error("Error while getCharacter from XMLAPI", e);
			throw e;
		}
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
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

}
