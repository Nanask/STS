package com.callor.todo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.callor.todo.config.QualifierConfig;
import com.callor.todo.model.TodoVO;
import com.callor.todo.service.FileServiceABS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	private final List<TodoVO> todoList = new ArrayList<TodoVO>();
	
	private final FileServiceABS fileService;
	
	
	public HomeController(FileServiceABS fileService) {
		this.fileService = fileService;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
//	���� �̹����� �̸��� home.jsp�� �Ѱ���
	@RequestMapping(value = {"/",""}, method = RequestMethod.POST)
	public String home(Model model,String to_text,@RequestParam("to_image") MultipartFile to_image) {
		
//		fileService.fileUp(to_image);
		
		Map<String,String> retFileName = fileService.fileUp(to_image);
		
		
		
		TodoVO vo = 
				TodoVO.builder().to_text(to_text)
				.to_sImage(retFileName.get(QualifierConfig.FILE_SERVICE.SAVENAME)).build();
				
		todoList.add(vo);
		model.addAttribute("TODOLIST",todoList);
		
		model.addAttribute("IMAGES",retFileName);
		
	// ���޹��� �̹����� �� �Ѿ������ Ȯ���غ���
	log.debug("TODO {}",to_text);
	log.debug("�̹��� {}", to_image.getOriginalFilename());
		return "home";
	}
	
	/**
	 * web ���� ���۵� �����͸� �޴� ���
	 * 1. �ܵ� ������ ���ؼ� �����͸� �ޱ�
	 * 		String, Long, Integer ��� ���������� ������ �ޱ�
	 * 		@RequestParam() �� ����Ͽ� �������� �����ؼ� �޴´�
	 * 		��Ȯ�ϰ� �����̸��� ��Ī�� �� �ְ�
	 * 		Ȥ�� �������� �߸�(type) ���޵Ǿ� 400 ������ ���� ���� ������ �� �ִ�.
	 * 		required = false �����ϰ� defalutValue �� �����Ͽ�
	 * 		400 ������ ������ �� �ִ�.
	 * 
	 * 2. Map<String, Object>�� ����Ͽ� ������ �ޱ�
	 * 		form ���� POST �� ���۵� �����ʹ� Map�� ����Ͽ�
	 * 		���� ������ �� �ִ�.
	 * 
	 * 		@RequstBody �� ����Ͽ� ������ �����Ѵ�
	 * 		@RequestBody �� �Ѱ��� �Ű����� ���� ����� �� �ִ�.
	 * 
	 * 3. VO(DTO) �� ����Ͽ� ������ �ޱ�
	 * 		form���� POST, GET, PUT, DELETE ������ ���۵� �����ʹ�
	 * 		��� ������ VO�� ���ؼ� �����͸� ������ �� �ִ�
	 * 		@ModelAttribute �� �����Ͽ� ��Ȯ�� ������ �����͸� ������ �� �ִ�.
	 * 		@ModelAttribute �� �����Ͽ�
	 * 		�����͸� �����Ϸ��� VO(DTO) Ŭ������ �ݵ�� 
	 * 		Getter, Setter, �ʵ������(AllarguContrurctor) �� �־�� �Ѵ�.
	 */
	
	@ResponseBody
	@RequestMapping(value = "/upload",method=RequestMethod.POST)
	public Map<String,String> upload(TodoVO todoVO,
			@RequestParam("to_image") MultipartFile to_image) {
		
		log.debug("TEXT {}", todoVO.getTo_text());
		log.debug("�̹���{}", to_image.getOriginalFilename());
		Map<String,String> retName = fileService.fileUp(to_image);
		return retName;
	}
}
