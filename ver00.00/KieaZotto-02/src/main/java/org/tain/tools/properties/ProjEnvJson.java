package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.json")
@Data
public class ProjEnvJson {

	private String name;  // default
	
	/*
	private String orgInfoFile;
	
	private String grpInfoFile;
	private String grpSvrInfoFile;
	private String svrInfoFile;
	
	private String roleInfoFile;
	private String roleUsrInfoFile;
	private String usrInfoFile;
	
	private String cmdInfoFile;
	
	private String cdMstInfoFile;
	private String cdItmInfoFile;
	
	private String brwInfoFile;
	*/
	
	private String dummy;  // null
}
