package org.tain.files;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

	//private final Path rootLocation = Paths.get("FILES");
	@Value("${spring.servlet.multipart.location}")
	private Path rootLocation;
	
	@Bean
	public void start() throws Exception {
		log.info("KANG-20210405 >>>>> START {}", CurrentInfo.get());
		if (Boolean.TRUE) this.deleteAll();
		if (Boolean.TRUE) this.init();
		log.info("KANG-20210405 >>>>> END   {}, rootLocation: {}", CurrentInfo.get(), this.rootLocation.getFileName());
	}
	
	///////////////////////////////////////////////////////////////////////////
	// init
	@Override
	public void deleteAll() throws Exception {
		FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
	}
	
	@Override
	public void init() throws Exception {
		Files.createDirectories(this.rootLocation);
	}

	///////////////////////////////////////////////////////////////////////////
	// list
	@Override
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// upload
	@Override
	public void store(MultipartFile file) throws Exception {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		if (file.isEmpty()) {
			throw new Exception("File not found...");
		}
		
		if (filename.contains("..")) {
			throw new Exception("Cannot store file...." + filename);
		}
		
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	///////////////////////////////////////////////////////////////////////////
	// download
	@Override
	public Resource loadAsResource(String filename) throws Exception {
		Path file = load(filename);
		Resource resource = new UrlResource(file.toUri());
		if (resource.exists() || resource.isReadable()) {
			return resource;
		}
		return null;
	}

	@Override
	public Path load(String filename) throws Exception {
		return this.rootLocation.resolve(filename);
	}
}
