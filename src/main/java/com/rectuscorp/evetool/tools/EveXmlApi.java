package com.rectuscorp.evetool.tools;

import com.rectuscorp.evetool.EveXmlApiObject;
import com.rectuscorp.evetool.entities.core.ApiKey;
import com.rectuscorp.evetool.entities.core.Character;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EveXmlApi {

	private static final Logger log = LogManager.getLogger(EveXmlApi.class);
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

	public static List<Character> getCharacterList(ApiKey apiKey) {
		List<Character> out = new ArrayList<Character>();
		GetMethod get = new GetMethod(EveXmlApiObject.API_URL + "account/Characters.xml.aspx?keyID=" + apiKey.getKeyId() + "&vCode=" + apiKey.getVerificationCode());
		return out;
	}

}
