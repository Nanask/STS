package com.callor.todo.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public abstract class FileServiceABS {
	
	// ���ε��� ������ ����� ����
	protected String fileUpPath;
	
	/**
	 * file-context.xml �� ������
	 * winFilePath, macFilePath ���� �����ͼ�
	 * ������ �����ϴ� �ڵ�
	 */
	
	@Autowired
	public void fileUpPath(String winPath) {
		File path = new File(winPath);
		if(path.exists()) {
			this.fileUpPath = winPath;
		}
	}
	
	public abstract String fileUp(MultipartFile file);
	public abstract List<String> filesUp(MultipartHttpServletRequest files);
}
