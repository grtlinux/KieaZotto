package org.tain.tools.enums;

public enum OptionType {
	RM_NULL  ("RM_NULL"          , 1),
	RM_SPACE ("RM_SPACE"         , 2),

	OPTION3  ("OPTION3"          , 4),
	OPTION4  ("OPTION4"          , 8),
	OPTION5  ("OPTION5"          , 16),
	OPTION6  ("OPTION6"          , 32),
	OPTION7  ("OPTION7"          , 64),
	OPTION8  ("OPTION8"          , 128),
	OPTION9  ("OPTION9"          , 256),
	OPTION10 ("OPTION10"         , 512),
	OPTION11 ("OPTION11"         , 1024),
	OPTION12 ("OPTION12"         , 2048),
	OPTION13 ("OPTION13"         , 4096),
	OPTION14 ("OPTION14"         , 8192),
	OPTION15 ("OPTION15"         , 16384),
	OPTION16 ("OPTION16"         , 32768);

	/*
	 * 0000 0000  0000 0000
	 * 0000 0000  0000 0000  0000 0000  0000 0000
	 */
	private String name;
	private int value;

	OptionType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return this.value;
	}
}
