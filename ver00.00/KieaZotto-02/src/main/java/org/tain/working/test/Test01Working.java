package org.tain.working.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

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
}
