package com.xio.clone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MpaUsrHomeController {
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "hi";
	}

	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "main2";
	}

	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public int showMain3(int a, int b) {
		int c = a + b;
		return c;
	}

	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4(int a, int b) {
		return a - b;
	}

	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public String showMain5() {
		return "-29";
	}

	@RequestMapping("/usr/home/main6")
	@ResponseBody
	public boolean showMain6(int a, int b) {
		return a > b;
	}

	@RequestMapping("/usr/home/main7")
	@ResponseBody
	public int[] showMain7() {
		int[] arr = { 1, 2, 3, 4, 5 };
		return arr;
	}

	@RequestMapping("/usr/home/main8")
	@ResponseBody
	public List<Integer> showMain8() {
		List<Integer> list = new ArrayList<>();

		list.add(11);
		list.add(22);
		list.add(33);

		return list;
	}

	@RequestMapping("/usr/home/main9")
	@ResponseBody
	public Map<String, Object> showMain9() {
		Map<String, Object> map = new HashMap<>();

		map.put("철수나이", 33);
		map.put("영희나이", 53);

		return map;
	}
	
	private int num = 0;
	
	@RequestMapping("/usr/home/main10")
	@ResponseBody
	public Map<String, Object> showMain10(){
		num++;
		
		Map<String, Object> map = new HashMap<>();
		map.put("숫자", num);
		
		return map;
	}
}
