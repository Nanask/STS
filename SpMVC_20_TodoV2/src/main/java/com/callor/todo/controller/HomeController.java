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

import com.callor.todo.service.FileServiceABS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	private final List<String> todoList = new ArrayList<String>();
	
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
		
		todoList.add(to_text);
		model.addAttribute("TODOLIST",todoList);
		
		model.addAttribute("IMAGES",retFileName);
		
	// ���޹��� �̹����� �� �Ѿ������ Ȯ���غ���
	log.debug("TODO {}",to_text);
	log.debug("�̹��� {}", to_image.getOriginalFilename());
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload",method=RequestMethod.POST)
	public Map<String,String> upload(String to_text,
			@RequestParam("to_image") MultipartFile to_image) {
		
		log.debug("TEXT {}", to_text);
		log.debug("�̹���{}", to_image.getOriginalFilename());
		Map<String,String> retName = fileService.fileUp(to_image);
		return retName;
	}
}
