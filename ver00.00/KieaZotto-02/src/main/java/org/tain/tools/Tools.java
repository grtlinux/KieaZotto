package org.tain.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Tools {

	@Bean
	public void startTools() throws Exception {
		log.info("KANG-20210405 >>>>> START {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
		}
		
		log.info("KANG-20210405 >>>>> END   {} {}", CurrentInfo.get());
	}
}
