package org.tain.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_prod")
@Data
public class TblProd {

	@Id
	private Long id;
	
	private String code;
	
	private String name;
	
	private String desc;
	
	private Integer price;
}
