package org.tain.working.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Test01Working {

	public void test0101() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220642503709
			// NIO(2) : 파일과 디렉토리(1) - 경로 정의(Path)
			Path path = Paths.get("src/sec01/exam01_path/PathExample.java");
			System.out.println(">>>>> toString(): " + path.toString());
			System.out.println(">>>>> FileName: " + path.getFileName());
			System.out.println(">>>>> Parent.FileName: " + path.getParent().getFileName());
			System.out.println(">>>>> NameCount: " + path.getNameCount());
			System.out.println();
			
			for (int i=0; i < path.getNameCount(); i++) {
				System.out.println(">>>>> " + path.getName(i));
			}
			System.out.println();
			
			Iterator<Path> iter = path.iterator();
			while (iter.hasNext()) {
				Path temp = iter.next();
				System.out.println(">>>>> " + temp.getFileName());
			}
		}
		
		if (Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220642517041
			// NIO(2) : 파일과 디렉토리(2) - 파일 시스템 정보(FileSystem)
		}
		
		if (Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220643207400
			// NIO(2) : 파일과 디렉토리(3) - 파일 속성 읽기 / 파일, 디렉토리 생성 과 삭제
		}
		
		if (Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220643246925
			// NIO(2) : 파일과 디렉토리(4) - 와치 서비스 ( WatchService )
		}
		
		if (Boolean.TRUE) {
			//
			//
		}
	}
	
	@SuppressWarnings("unused")
	public void test0102() throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		if (Boolean.TRUE) {
			File file1 = new File("/home/user/test.txt");
			Path path1 = Paths.get("/home/user");
			Path path2 = Paths.get("/home/user/test.txt");
			Path path3 = file1.toPath();  // File -> Path
			File file2 = path2.toFile();  // Path -> File
			
			try {
				if (!Files.isDirectory(path1)) {
					Files.createDirectories(path1);
				}
				
				if (!Files.exists(path2)) {
					Files.createFile(path2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Boolean.TRUE) {
			// walk
			Path path = Paths.get("/home/users/hello/world");
			List<Path> lst = null;
			try (Stream<Path> walk = Files.walk(path)) {
				lst = walk
						.filter(file -> Files.isRegularFile(file))
						.collect(Collectors.toList());
			}
			lst.forEach(file -> System.out.println(file));
		}
		
		if (Boolean.TRUE) {
			// relativize
			Path path1 = Paths.get("/home/users/hello/world");
			Path path2 = Paths.get("/home/users/hello/world/My/Name/is/Kang");
			Path path3 = path1.relativize(path2);
			System.out.println(">>>>> path1: " + path1);  // /home/users/hello/world
			System.out.println(">>>>> path2: " + path2);  // /home/users/hello/world/My/Name/is/Kang
			System.out.println(">>>>> path3: " + path3);  //                         My/Name/is/Kang
		}
		
		if (Boolean.TRUE) {
			// walk filter
			Path path = Paths.get("/home/users/hello/world");
			if (!Files.isDirectory(path)) {
				throw new IllegalArgumentException("Path must be a directory.");
			}
			
			List<Path> lst = null;
			try (Stream<Path> walk = Files.walk(path)) {
				lst = walk
						.filter(Files::isReadable)  // read permission
						.filter(Files::isRegularFile)  // is a file
						.filter(p -> p.getFileName().toString().endsWith(".txt"))
						.collect(Collectors.toList());
			}
			lst.forEach(file -> System.out.println(file));
		}
		
		if (Boolean.TRUE) {
			// use function
			Path path = Paths.get("/home/users/hello/world");
			long fileSize = 1024 * 1024 * 10;  // 10MB
			List<Path> lst = findByFileSize(path, fileSize);
			lst.forEach(x -> System.out.println(x));
		}
		
		if (Boolean.TRUE) {
			// set all files to be modified new date
			Path path = Paths.get("/home/users/hello/world");
			Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
			setAllFilesModifiedDate(path, yesterday);
		}
	}
	
	// collect files less then fileSize
	private List<Path> findByFileSize(Path path, long fileSize) throws Exception {
		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException("Path must be a directory..");
		}
		
		List<Path> lst;
		// walk file tree, no more recursive loop
		try (Stream<Path> walk = Files.walk(path)) {
			lst = walk
					.filter(Files::isReadable)  // read permission
					.filter(p -> !Files.isDirectory(p))  // is a file
					.filter(p -> checkFileSize(p, fileSize))
					.collect(Collectors.toList());
		}
		
		return lst;
	}
	
	// check file size, if more than fileSize, then return true
	private boolean checkFileSize(Path path, long fileSize) {
		boolean result = false;
		try {
			if (Files.size(path) >= fileSize) {
				result = true;
			}
		} catch (IOException e) {
			System.err.println("Unable to get the file size of this file: " + path);
		}
		return result;
	}
	
	// set all files' last modified time to this instant
	private void setAllFilesModifiedDate(Path path, Instant instant) throws IOException {
		try (Stream<Path> walk = Files.walk(path)) {
			walk
				.filter(Files::isReadable)   // read permission
				.filter(Files::isRegularFile)  // file only
				.forEach(p -> {
					try {
						// set last modified time
						Files.setLastModifiedTime(p, FileTime.from(instant));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
		}
	}
}
