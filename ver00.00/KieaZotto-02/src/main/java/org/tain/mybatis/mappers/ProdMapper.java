package org.tain.mybatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProdMapper {

	List<Map<String,Object>> selectAll(Map<String,Object> mapIn);
}
