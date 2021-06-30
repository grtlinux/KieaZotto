package org.tain.working.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.working.load.tables.TblCustLoader;
import org.tain.working.load.tables.TblCustProdLoader;
import org.tain.working.load.tables.TblProdLoader;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoadWorking {

	public void doing() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			if (Boolean.TRUE) loadTblCust();
			if (Boolean.TRUE) loadTblProd();
			if (Boolean.TRUE) loadTblCustProd();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblCustLoader tblCustLoader;
	
	private void loadTblCust() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblCustLoader.loading();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblProdLoader tblProdLoader;
	
	private void loadTblProd() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblProdLoader.loading();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private TblCustProdLoader tblCustProdLoader;
	
	private void loadTblCustProd() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) this.tblCustProdLoader.loading();
	}
}
