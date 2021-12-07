package com.callor.todo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.todo.config.QualifierConfig;
import com.callor.todo.service.FileServiceABS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Service("fileServiceV1")
@Service(QualifierConfig.SERVICE.FILE_SERVICE_V1)

public class FileServiceImplV1 extends FileServiceABS{

	/**
	 * fileUp()
	 * 1개의 파일을 서버의 특정폴더(fileUpPath)에 업로드하기
	 * 
	 * 1. fileUpPath가 정상적으로 있는지 검사
	 * 2. 폴더가 없으면 폴더 생성하기
	 * 3. 원본파일이름을 겹쳐서 업로드 되지 않도록 방지정책
	 * 	UUID + 파일이름 형식으로 파일명을 변경하여 저장
	 * 4. 저장이 완료되면 저장된 파일명을 return
	 * 
	 */
	@Override
	public Map<String, String> fileUp(MultipartFile file) {
		// TODO Auto-generated method stub
		
		// 파일이 정확시 상속됐는지 확인하는 log
		log.debug("파일업로드 path {}", this.fileUpPath);
		
		// 파일정보가 null이면 더이상 진행하지 말기
		if(file.isEmpty()) {
			log.debug("파일없음 path {}", this.fileUpPath);
			return null;
		}
//		if(file == null) {
//			return null;
//		}
		
		File dir = new File(fileUpPath);
		// 업로드할 폴더가 없으면
		if(!dir.exists()) {
			dir.mkdirs(); // 폴더생성하기
		}
		
		// 유일한 UUID를 추출
		String strUUID = UUID.randomUUID().toString();
		String originalFileName = file.getOriginalFilename();
		// 기존의 string format을 이용하여 이름 생성하기
		// UUID + "-" + 원래이름
		String saveFileName = String.format("%s-%s", strUUID , originalFileName);
		
		// 저장할 폴더와 파일이름을 매개변수로 전달하여
		// 파일을 저장하기 위하여 File 객체 생성
		File uploadFile = new File(fileUpPath, saveFileName);
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {

			e.printStackTrace();
		}
		
//		저장된 파일이름과 파일을 map형태로 담아서 리턴해줬음
		Map<String, String> retFileName
			= new HashMap<String,String>();	
	
		retFileName.put(QualifierConfig.FILE_SERVICE.ORIGINAL, originalFileName);
		
		retFileName.put(QualifierConfig.FILE_SERVICE.SAVENAME, saveFileName);
		
		return retFileName;
//		return saveFileName;
//		return null;
	}

	@Override
	public List<Map<String, String>> filesUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
