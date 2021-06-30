package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.job")
@Data
public class ProjEnvJob {

	private String name;  // default
	
	/*
	
	 */
	
	private String dummy;  // null
}
