package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.entities.core.ApiKey;
import com.rectuscorp.evetool.entities.core.Character;
import com.rectuscorp.evetool.entities.crest.Corporation;
import com.rectuscorp.evetool.service.IserviceCorporation;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class EveXmlApi {

	private static final Logger log = LogManager.getLogger(EveXmlApi.class);
	public static String API_URL = "https://api.eveonline.com/";
	@SpringBean(name = "serviceCorporation")
	private static IserviceCorporation serviceCorporation;
	private static EveXmlApi ourInstance;
	private HttpClient client = new HttpClient();

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
	 * retrive character by apiKey
	 *
	 * @param apiKey
	 * @return
	 */
	public static List<Character> getCharacterList(ApiKey apiKey) {
		List<Character> out = new ArrayList<Character>();
		GetMethod get = new GetMethod(API_URL + "account/Characters.xml.aspx?keyID=" + apiKey.getKeyId() + "&vCode=" + apiKey.getVerificationCode());
		try {
			String xmlResponse = get.getResponseBodyAsString();
			SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance());
			DOMDocument document = (DOMDocument) saxReader.read(new StringReader(xmlResponse));
			for (DOMElement charElement : (List<DOMElement>) document.selectNodes("//row ")) {
				Character character = new Character();
				character.setName(charElement.getAttribute("name"));
				if (charElement.getAttribute("corporationID") != null) {
					Corporation corpo = serviceCorporation.get(Long.parseLong(charElement.getAttribute("corporationID")));
					character.setCorporation(corpo);
				}
				out.add(character);
			}
		} catch (Exception e) {
			log.error("Error while api request", e);
		}
		return out;
	}

}
