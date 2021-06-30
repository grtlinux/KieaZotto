package org.tain.tools.enums;

public enum JsonPrintType {
	NORMAL("Normal"),
	STEP01("STEP01"),
	STEP02("STEP02"),
	STEP03("STEP03"),
	DEFAULT("DEFAULT");

	private String value;

	JsonPrintType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
