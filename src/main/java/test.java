import com.rectuscorp.evetool.api.EveCRESTApi;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.ajax.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class test {
	private static final Logger log = LogManager.getLogger(test.class);

	public static void main(String[] args) {
		HttpClient client = new HttpClient();
		try {
			//GetMethod get = new GetMethod( CrestObject.API_URL + "market/groups/");
			GetMethod get = new GetMethod(EveCRESTApi.API_URL + "regions/");
			client.executeMethod(get);
			String resp = get.getResponseBodyAsString();
			JSONObject jsonObj = new JSONObject(resp);
			ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
			for (int i = 0; i < jsonObj.getJSONArray("items").length(); i++) {
				JSONObject temp = (JSONObject) jsonObj.getJSONArray("items").get(i);
				if (!temp.has("parentGroup"))
					jsonObjects.add(temp);
			}
			Collections.sort(jsonObjects, new Comparator<JSONObject>() {
				public int compare(JSONObject o1, JSONObject o2) {
					try {
						return ((String) o1.get("name")).compareTo(((String) o2.get("name")));
					} catch (Exception e) {
						return 0;
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
