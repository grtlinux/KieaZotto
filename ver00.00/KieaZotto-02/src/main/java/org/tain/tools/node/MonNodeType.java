package org.tain.tools.node;

public enum MonNodeType {
	OBJECT_NODE("ObjectNode"),
	ARRAY_NODE("ArrayNode"),
	OBJECT_VALUE("ObjectValue");

	private String value;

	MonNodeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
