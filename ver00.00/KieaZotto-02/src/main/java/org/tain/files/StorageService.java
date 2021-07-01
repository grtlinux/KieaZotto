package org.tain.files;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init() throws Exception;
	
	void store(MultipartFile file) throws Exception;
	
	Stream<Path> loadAll() throws Exception;
	
	Resource loadAsResource(String filename) throws Exception;
	
	Path load(String filename) throws Exception;
	
	void deleteAll() throws Exception;
}
