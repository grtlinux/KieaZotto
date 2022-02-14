package org.tain.working;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.load.LoadWorking;
import org.tain.working.properties.PropertiesWorking;
import org.tain.working.testNio.Test01Working;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Working {

	public void doing() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) jobProperties();
		if (Boolean.TRUE) jobLoad();
		//if (Boolean.TRUE) jobFileupload();
		if (Boolean.TRUE) jobTest01();
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
	
	///////////////////////////////////////////////////////////////////////////
	
	/*
	@Autowired
	private StorageService storageService;
	
	private void jobFileupload() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.storageService.deleteAll();
		if (Boolean.TRUE) this.storageService.init();
	}
	*/
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private Test01Working test01Working;
	
	private void jobTest01() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (!Boolean.TRUE) this.test01Working.test0101();
		if (!Boolean.TRUE) this.test01Working.test0102();
		if (Boolean.TRUE) this.test01Working.test0104();
	}
}
