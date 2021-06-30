package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.load.LoadWorking;
import org.tain.working.properties.PropertiesWorking;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void doing() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) jobProperties();
		if (Boolean.TRUE) jobLoad();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private PropertiesWorking propertiesWorking;
	
	private void jobProperties() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.propertiesWorking.doing();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private LoadWorking loadWorking;
	
	private void jobLoad() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.loadWorking.doing();
	}
}
