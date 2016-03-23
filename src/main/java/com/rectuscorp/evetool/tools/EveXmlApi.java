package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.core.ApiKey;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.service.IserviceCorporation;
import com.rectuscorp.evetool.spring.AppContext;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.request.Url;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EveXmlApi {

	private static final Logger log = LogManager.getLogger(EveXmlApi.class);
	public static String API_URL = "https://api.eveonline.com/";
	private static EveXmlApi ourInstance;
	private HttpClient client = new HttpClient();
	private static IserviceCorporation serviceCorporation;

	public EveXmlApi() {
		ApplicationContext context = AppContext.getApplicationContext();
		serviceCorporation = (IserviceCorporation) context.getBean("serviceCorporation");
	}

	/**
	 * Singleton Getter
	 *
	 * @return EveXmlApi
	 */
	public static EveXmlApi get() {
		if (ourInstance == null)
			return new EveXmlApi();
		return ourInstance;
	}

	/**
	 * retreive character by apiKey
	 *
	 * @param apiKey
	 * @return
	 */
	public List<Character> getCharacterList(ApiKey apiKey) {
		List<Character> out = new ArrayList<Character>();
		GetMethod get = new GetMethod(API_URL + "account/Characters.xml.aspx?keyID=" + apiKey.getKeyId() + "&vCode=" + apiKey.getVerificationCode());
		try {
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//row ")) {
				Character character = new Character();
				character.setId(Long.parseLong(charElement.getAttribute("characterID")));
				character.setName(charElement.getAttribute("name"));
				if (charElement.getAttribute("corporationID") != null) {
					//Application.get()
					Corporation corpo = serviceCorporation.get(Long.parseLong(charElement.getAttribute("corporationID")));
					character.setCorporation(corpo);
				}
				character.setApiKey(apiKey);
				out.add(character);
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

	/**
	 * retreive free available corporation data from XML api
	 *
	 * @param corporationId	id to request
	 * @return Corporation object
	 */
	public Corporation getCorporation(String corporationId){
		return getCorporation(null, corporationId);
	}


	/**
	 * retreive corporation information
	 *
	 * @param apiKey    	apikey to use
	 * @param corporationID corporation id to get
	 * @return corporation object
	 */
	public Corporation getCorporation(ApiKey apiKey, String corporationID) {
		Corporation out = null;
		try {
			URIBuilder url = new URIBuilder(API_URL + "corp/CorporationSheet.xml.aspx");
			if(apiKey != null){
				url.addParameter("keyID",apiKey.getKeyId());
				url.addParameter("vCode",apiKey.getVerificationCode());
			}
			url.addParameter("corporationID", corporationID);
			GetMethod get = new GetMethod(url.toString());
			client.executeMethod(get);
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			if(document.selectSingleNode("//result") != null){
				Corporation corp = new Corporation();
				if(document.selectSingleNode("//result/corporationID") != null)
					corp.setId(Long.parseLong(document.selectSingleNode("//result/corporationID").getText()));
				if(document.selectSingleNode("//result/corporationName") != null)
					corp.setName(document.selectSingleNode("//result/corporationName").getText());
				if(document.selectSingleNode("//result/ticker") != null)
					corp.setTicker(document.selectSingleNode("//result/ticker").getText());
				if(document.selectSingleNode("//result/description") != null)
					corp.setDescription(document.selectSingleNode("//result/description").getText());
				if(document.selectSingleNode("//result/url") != null)
					corp.setUrl(document.selectSingleNode("//result/url").getText());
				if(document.selectSingleNode("//result/taxRate") != null)
					corp.setTaxRate(Double.parseDouble(document.selectSingleNode("//result/taxRate").getText()));
				if(document.selectSingleNode("//result/memberCount") != null)
					corp.setMemberCount(Integer.parseInt(document.selectSingleNode("//result/memberCount").getText()));
				if(document.selectSingleNode("//result/shares") != null)
					corp.setShares(Integer.parseInt(document.selectSingleNode("//result/memberCount").getText()));
				out = corp;
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

}
