package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.url")
@Data
public class ProjEnvUrl {

	private String name;  // default
	
	/*
	private String wsWrkUri;
	private String wsCmdUri;
	private String wsMonUri;
	
	private String wrkUrl;
	private String cmdUrl;
	private String monUrl;
	*/
	
	private String dummy;  // null
}
