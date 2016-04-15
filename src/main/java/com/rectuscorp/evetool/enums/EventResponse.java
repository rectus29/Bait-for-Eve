package com.rectuscorp.evetool.enums;

public enum EventResponse {
	UNDECIDED ("Undecided"),
	ACCEPTED("Accepted"),
	DECLINED("Declined"),
	TENTATIVE("Tentative");

	private String value ="";

	EventResponse(String value) {
		this.value = value;
	}
}
