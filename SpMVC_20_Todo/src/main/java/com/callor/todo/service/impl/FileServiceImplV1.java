package com.callor.todo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
	 * 1���� ������ ������ Ư������(fileUpPath)�� ���ε��ϱ�
	 * 
	 * 1. fileUpPath�� ���������� �ִ��� �˻�
	 * 2. ������ ������ ���� �����ϱ�
	 * 3. ���������̸��� ���ļ� ���ε� ���� �ʵ��� ������å
	 * 	UUID + �����̸� �������� ���ϸ��� �����Ͽ� ����
	 * 4. ������ �Ϸ�Ǹ� ����� ���ϸ��� return
	 * 
	 */
	@Override
	public String fileUp(MultipartFile file) {
		// TODO Auto-generated method stub
		
		// ������ ��Ȯ�� ��ӵƴ��� Ȯ���ϴ� log
		log.debug("���Ͼ��ε� path {}", this.fileUpPath);
		
		// ���������� null�̸� ���̻� �������� ����
		if(file == null) {
			return null;
		}
		
		File dir = new File(fileUpPath);
		// ���ε��� ������ ������
		if(!dir.exists()) {
			dir.mkdirs(); // ���������ϱ�
		}
		
		// ������ UUID�� ����
		String strUUID = UUID.randomUUID().toString();
		String originalFileName = file.getOriginalFilename();
		// ������ string format�� �̿��Ͽ� �̸� �����ϱ�
		// UUID + "-" + �����̸�
		String saveFileName = String.format("%s-%s", strUUID , originalFileName);
		
		// ������ ������ �����̸��� �Ű������� �����Ͽ�
		// ������ �����ϱ� ���Ͽ� File ��ü ����
		File uploadFile = new File(fileUpPath, saveFileName);
		try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException | IOException e) {

			e.printStackTrace();
		}
		return saveFileName;
	}

	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
