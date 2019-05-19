package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", boardService.getList());
		return "board/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "board/register";
	}
	@PostMapping("/register")
	public String register(BoardVo vo) {
		boardService.register(vo);
		return "board/list";
	}
	
}
