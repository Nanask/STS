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
 * �߻�Ŭ����
 * 1. �������̽�ó�� �ٸ� Ŭ������ ���赵 ����
 * 2. �߿��� method�� ���� ����
 * 	����� �� �ֵ��� ������ Ŭ����
 * 
 *
 */

@Slf4j
public abstract class FileServiceABS {
	
	// ���ε��� ������ ����� ����
	protected String fileUpPath;
	
	/**
	 * file-context.xml �� ������
	 * winFilePath, macFilePath ���� �����ͼ�
	 * ������ �����ϴ� �ڵ�
	 */
	
	@Autowired
	public void fileUpPath(String winPath, String macPath) {
		this.fileUpPath = macPath;
		
		// �ý��� ��������
		// �ü���� �̸��� �������� �ڵ�
		// �ü�� �̸��� �����ͼ� �빮�ڷ� ��ȯ : toUpperCase();
		String osName = System.getProperty("os.name").toUpperCase();
		
		log.debug("���� �ü�� {}" , osName);
		
		// �ü�� �̸��� WIN �ܾ ���ԵǾ� ������
		
		if(osName.contains("WIN")) {
			// �ü���� ���� ����Ǵ� ��ġ �����ϱ�
			this.fileUpPath = winPath;
		}
		
		log.debug("���� ��������{}", this.fileUpPath);
//		File path = new File(winPath);
//		if(path.exists()) {
//			this.fileUpPath = winPath;
//		}
	}
	
	public abstract Map<String, String> fileUp(MultipartFile file);
	public abstract List<Map<String,String>> filesUp(MultipartHttpServletRequest files);
}
