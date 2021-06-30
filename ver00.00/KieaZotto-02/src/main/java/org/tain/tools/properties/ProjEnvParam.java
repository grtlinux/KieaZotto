package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.param")
@Data
public class ProjEnvParam {

	private String name;  // default
	
	private String home;
	private String base;
	private String infoPath;
	private String dataPath;
	
	private String basePath;
	private String custFile;
	private String prodFile;
	private String custProdFile;
	
	private String dummy;  // null
}
