package com.rectuscorp.evetool.entities.crest;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {

	private String name;
	private int securityStatus;
	private String securityClass;
	private List<Planet> planetList = new ArrayList<Planet>();
	private List<Sovereignty> sovereigntyList = new ArrayList<Sovereignty>();

}
