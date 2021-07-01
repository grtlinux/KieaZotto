package org.tain.files;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	// init
	void deleteAll() throws Exception;
	void init() throws Exception;
	
	// list
	Stream<Path> list() throws Exception;
	
	// upload
	void store(MultipartFile file) throws Exception;
	
	// download
	Resource loadAsResource(String filename) throws Exception;
	Path load(String filename) throws Exception;
}
