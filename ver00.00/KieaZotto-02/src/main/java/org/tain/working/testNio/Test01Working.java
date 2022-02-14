package org.tain.working.testNio;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Test01Working {

	@SuppressWarnings("unused")
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
			FileSystem fileSystem = FileSystems.getDefault();
			
			for (FileStore store : fileSystem.getFileStores()) {
				System.out.println(">>>>> name: " + store.name());
				System.out.println(">>>>> type: " + store.type());
				System.out.println(">>>>> total: " + store.getTotalSpace());
				System.out.println(">>>>> used: " + (store.getTotalSpace() - store.getUnallocatedSpace()));
				System.out.println(">>>>> usable: " + store.getUsableSpace());
				System.out.println();
			}
			
			System.out.println(">>>>> separator: " + fileSystem.getSeparator());
			System.out.println();
			
			for (Path path : fileSystem.getRootDirectories()) {
				System.out.println(">>>>> * : " + path.toString());
			}
		}
		
		if (Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220643207400
			// NIO(2) : 파일과 디렉토리(3) - 파일 속성 읽기 / 파일, 디렉토리 생성 과 삭제
			Path path = Paths.get("/Users/kang-air/FILES/ajax_sample.tar");
			System.out.println(">>>>> isDirectory: " + Files.isDirectory(path));
			System.out.println(">>>>> isRegularFile: " + Files.isRegularFile(path));
			System.out.println(">>>>> getLastModifiedTime: " + Files.getLastModifiedTime(path));
			System.out.println(">>>>> size: " + Files.size(path));
			System.out.println(">>>>> getOwner: " + Files.getOwner(path));
			System.out.println(">>>>> isHidden: " + Files.isHidden(path));
			System.out.println(">>>>> isReadable: " + Files.isReadable(path));
			System.out.println(">>>>> isWritable: " + Files.isWritable(path));
			System.out.println();
			
			Path path1 = Paths.get("/Users/kang-air/FILES");
			Path path2 = Paths.get("/Users/kang-air/FILES/ajax_sample.tar");
			Path path3 = Paths.get("/Users/kang-air/FILES");
			
			if (Files.notExists(path1)) {
				Files.createDirectories(path1);
			}
			if (Files.notExists(path2)) {
				Files.createFile(path2);
			}
			
			DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path3);
			for (Path path4 : directoryStream) {
				if (Files.isDirectory(path4)) {
					System.out.println(">>>>> dir : " + path4.getFileName());
				} else {
					System.out.println(">>>>> file: " + path4.getFileName());
				}
			}
		}
		
		if (!Boolean.TRUE) {
			// REF: https://m.blog.naver.com/rain483/220643246925
			// NIO(2) : 파일과 디렉토리(4) - 와치 서비스 ( WatchService )
			try {
				Path path = Paths.get("/home/users");
				WatchService watchService = path.getFileSystem().newWatchService();
				path.register(watchService
						, StandardWatchEventKinds.ENTRY_CREATE
						, StandardWatchEventKinds.ENTRY_MODIFY
						, StandardWatchEventKinds.ENTRY_DELETE
						);
				// loop forever to watch directory
				while (Boolean.TRUE) {
					WatchKey watchKey = watchService.take();  // This call is blocking until events are present.
					
					// create the list of path files
					List<String> fimlesLog = new ArrayList<>();
				}
			} catch (InterruptedException e) {
				System.err.println("Directory watcher thread interrupted...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Boolean.TRUE) {
			//
			//
		}
	}
	
	@SuppressWarnings({ "unused", "resource" })
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
		
		if (Boolean.TRUE) {
			// walk
			Path rootPath = Paths.get("/home/users");
			Stream<Path> paths = Files.walk(rootPath, 3, FileVisitOption.FOLLOW_LINKS);
			paths.limit(10).forEach(System.out::println);
		}
	}
	
	public void test0103() throws Exception {
		String dir = "/home/users/dir";
		
		WatchService service = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(dir);
		path.register(service,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY
				);
		while (true) {
			WatchKey key = service.take();
			List<WatchEvent<?>> list = key.pollEvents();  //이벤트를 방을 때까지 기다림.
			for (WatchEvent<?> event : list) {
				Kind<?> kind = event.kind();
				Path pth = (Path) event.context();
				if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					// 파일이 생성되었을 때 실행되는 코드
					System.out.println(">>>>> CREATE: " + pth.getFileName());
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
					// 파일이 삭제되었을 때 실행되는 코드
					System.out.println(">>>>> DELETE: " + pth.getFileName());
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
					// 파일이 수정되었을 때 실행되는 코드
					System.out.println(">>>>> MODIFY: " + pth.getFileName());
				} else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
					// 운영체제에서 이벤트가 소실되었거나 버려질 경우에 발생되는코드
					System.out.println(">>>>> OVERFLOW ");
				}
			}
			
			if (!key.reset())
				break;
		}
		service.close();
	}
	
	public void test0104() throws Exception {
		log.info("KANG-20220214 >>>>> {} {}", CurrentInfo.get());

		if (Boolean.TRUE) {
			// {"code":"C002","val":"123"}
			String txt = "{\"code\":\"C002\",\"val\":\"123\"}";
			System.out.println(">>> 1: " +  new String(Base64.getUrlEncoder().encode(txt.getBytes())));
			System.out.println(">>> 2: " +  new String(Base64.getEncoder().encode(txt.getBytes())));
			System.out.println(">>> 3: " +  URLEncoder.encode(txt, "utf-8"));
			//System.out.println(">>> enc1: " + enc1);
			
			// %7B%22code%22%3A%22C002%22%2C%22val%22%3A%22123%22%7D=
			String enc2 = "%7B%22code%22%3A%22C002%22%2C%22val%22%3A%22123%22%7D=";
			System.out.println(">>> 3: " + URLDecoder.decode(enc2, "utf-8"));
			
		}
	}
	
	public void watchVideoCamera(Path path) throws Exception {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		
		OUTERMOST:
		while (true) {
			//final WatchKey watchKey = watchService.poll();
			final WatchKey watchKey = watchService.poll(11, TimeUnit.SECONDS);
			
			if (watchKey == null) {
				System.out.println("The video camera is jammed - security watch system is canceled!");
				break;
			} else {
				for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
					final Kind<?> kind = watchEvent.kind();
					
					if (kind == StandardWatchEventKinds.OVERFLOW)
						continue;
					
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						// get the filename for the event
						@SuppressWarnings("unchecked")
						final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
						final Path filename = watchEventPath.context();
						final Path child = path.resolve(filename);
						
						if (Files.probeContentType(child).equals("image/jpeg")) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
							System.out.println("Video capture successfully at: " + dateFormat.format(new Date()));
						} else {
							System.out.println("The Video camera capture format failed.");
							break OUTERMOST;
						}
					}
					
					boolean valid = watchKey.reset();
					if (!valid)
						break;
				}
			}
		}
		
		watchService.close();
	}
	
	public void watchRNDir(Path path) throws Exception {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
			path.register(watchService
					, StandardWatchEventKinds.ENTRY_CREATE
					, StandardWatchEventKinds.ENTRY_MODIFY
					, StandardWatchEventKinds.ENTRY_DELETE);
			
			while (true) {
				// retrieve and remove the next watch key
				final WatchKey key = watchService.take();

				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					final Kind<?> kind = watchEvent.kind();
					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}
					@SuppressWarnings("unchecked")
					final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
					final Path filename = watchEventPath.context();
					System.out.println(kind + " -> " + filename);
				}

				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
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

