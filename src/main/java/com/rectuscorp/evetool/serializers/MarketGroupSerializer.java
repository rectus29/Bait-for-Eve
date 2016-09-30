package com.rectuscorp.evetool.serializers;

import com.google.gson.*;
import com.rectuscorp.evetool.entities.crest.MarketGroup;

import java.lang.reflect.Type;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 23/09/2016 12:03                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class MarketGroupSerializer implements JsonSerializer<MarketGroup>, JsonDeserializer<MarketGroup> {

	@Override
	public MarketGroup deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
			throws JsonParseException {
		return null;
	}

	@Override
	public JsonElement serialize(MarketGroup marketGroup, Type type, JsonSerializationContext jsonSerializationContext) {
		Gson gson = new GsonBuilder()
				//.registerTypeHierarchyAdapter(GenericEntity.class, new GenericEntitySerialier())
				.create();
		return gson.toJsonTree(marketGroup);
	}
}
