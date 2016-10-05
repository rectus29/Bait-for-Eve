package com.rectuscorp.evetool.api;

import com.rectuscorp.evetool.entities.crest.Type;
import com.rectuscorp.evetool.web.page.admin.api.panels.crestAPIPanel.CrestAPIPanel;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EveCRESTApiTest extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

	}

	@After
	public void tearDown() throws Exception {

	}

	public void testGet() throws Exception {

	}

	public void testGetAllRegions() throws Exception {

	}

	public void testGetRegion() throws Exception {

	}

	public void testGetAllConstellations() throws Exception {

	}

	public void testGetConstellation() throws Exception {

	}

	public void testGetSolarSystems() throws Exception {

	}

	public void testGetSolarSystem() throws Exception {

	}

	public void testGetAlliance() throws Exception {

	}

	@Test
	public void testGetType() throws Exception {
		String testJson = "{\"capacity\": 0.0, \"description\": \"Le plagioclase riche, dont le rendement est "
				+	 "sup\\u00e9rieur de 10 % \\u00e0 celui du minerai classique, est un minerai tr\\u00e8s r\\u00e9mun\\u00e9rateur pour les mineurs.\\n\\nLe plagioclase n'est certainement pas le minerai brut le plus pr\\u00e9cieux, mais il contient une grande quantit\\u00e9 de py\\u00e9rite. C'est pour cette raison que sa demande ne faiblit jamais. Il contient \\u00e9galement du tritanium et du mexallon.\\n\\nCe minerai est une version condens\\u00e9e, beaucoup plus dense que le minerai brut d'origine.\", \"portionSize_str\": \"1\", \"iconID\": 3320, \"portionSize\": 1, \"iconID_str\": \"3320\", \"volume\": 0.15, \"dogma\": {\"attributes\": [{\"attribute\": {\"id_str\": \"4\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/4/\", \"id\": 4, \"name\": \"mass\"}, \"value\": 1e+35}, {\"attribute\": {\"id_str\": \"38\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/38/\", \"id\": 38, \"name\": \"capacity\"}, \"value\": 0.0}, {\"attribute\": {\"id_str\": \"161\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/161/\", \"id\": 161, \"name\": \"volume\"}, \"value\": 0.15}, {\"attribute\": {\"id_str\": \"162\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/162/\", \"id\": 162, \"name\": \"radius\"}, \"value\": 0.0}, {\"attribute\": {\"id_str\": \"182\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/182/\", \"id\": 182, \"name\": \"requiredSkill1\"}, \"value\": 3386.0}, {\"attribute\": {\"id_str\": \"790\", \"href\": \"https://crest-tq.eveonline.com/dogma/attributes/790/\", \"id\": 790, \"name\": \"reprocessingSkillType\"}, \"value\": 12191.0}]}, \"radius\": 1.0, \"id_str\": \"28423\", \"published\": true, \"mass\": 1e+35, \"id\": 28423, \"name\": \"Plagioclase riche compress\\u00e9\"}";
		Type type = EveCRESTApi.get().getType("28423");
	}

	public void testGetAttribute() throws Exception {

	}

	public void testGetCorporation() throws Exception {

	}

	public void testGetAllMarketGroup() throws Exception {

	}

	//@Test
	public void testGetMarketGroup() throws Exception {
		String testJson = "";
	}
}