package com.callor.todo.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 403
 * 추상클래스
 * 1. 인터페이스처럼 다른 클래스의 설계도 역할
 * 2. 중요한 method는 직접 만들어서
 * 	사용할 수 있도록 구성된 클래스
 * 
 *
 */

@Slf4j
public abstract class FileServiceABS {
	
	// 업로드한 파일이 저장될 폴더
	protected String fileUpPath;
	
	/**
	 * file-context.xml 에 설정된
	 * winFilePath, macFilePath 값을 가져와서
	 * 변수에 세팅하는 코드
	 */
	
	@Autowired
	public void fileUpPath(String winPath, String macPath) {
		this.fileUpPath = macPath;
		
		// 시스템 변수에서
		// 운영체제의 이름을 가져오는 코드
		// 운영체제 이름을 가져와서 대문자로 변환 : toUpperCase();
		String osName = System.getProperty("os.name").toUpperCase();
		
		log.debug("현재 운영체제 {}" , osName);
		
		// 운영체제 이름에 WIN 단어가 포함되어 있으면
		
		if(osName.contains("WIN")) {
			// 운영체제에 따라서 저장되는 위치 변경하기
			this.fileUpPath = winPath;
		}
		
		log.debug("파일 저장폴더{}", this.fileUpPath);
//		File path = new File(winPath);
//		if(path.exists()) {
//			this.fileUpPath = winPath;
//		}
	}
	
	public abstract Map<String, String> fileUp(MultipartFile file);
	public abstract List<Map<String,String>> filesUp(MultipartHttpServletRequest files);
}
