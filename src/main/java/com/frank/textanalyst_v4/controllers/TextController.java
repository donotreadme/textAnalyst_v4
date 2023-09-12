package com.frank.textanalyst_v4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frank.textanalyst_v4.domain.Text;
import com.frank.textanalyst_v4.repositories.TextRepository;
import com.frank.textanalyst_v4.services.TextService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("text")
public class TextController {

	private final TextService textService;
	private TextRepository textRepository;

	@Autowired
	public TextController(TextService textService, TextRepository textRepository) {
		this.textService = textService;
		this.textRepository = textRepository;
	}

	@GetMapping
	public List<Text> getText() {
		return textService.saveFromFileSystem("D:/USB Backup/Text/proofed", true); // TODO that's just for testing	
	}
	
	@GetMapping(path = "/isProofed")
	public List<Text> getProofedDepending(@RequestParam(value = "value", required = true) boolean isProofed) {
		if (isProofed) {
			return textService.getAllProofRead();
		}else {
			return textService.getAllNotProofRead();
		}
	}

	@GetMapping(path = "/{textId}")
	public Text getTextById(@PathVariable("textId") int id) {
		return textService.getTextById(id);
	}

	@GetMapping(path = "/findByTitle")
	public List<Text> getTextByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
		// TODO testing
		if (title != "") {
			return textService.getTextbyTitle(title);
		}else {
			throw new IllegalArgumentException("The argument can not be null");
		}
		
	}

	@GetMapping(path = "/{textId}/tagged")
	public Text getTaggedText(@PathVariable("textId") int id) {
		return textService.getTaggedText(id);
	}

	@PostMapping(path = "/add")
	public @ResponseBody int addNewText(@RequestBody Text text) {
		Text r = textService.addText(text);
		return r.getId();
	}

	@PutMapping(path = "/{textId}/update")
	public void updateStudent(@PathVariable("textId") int textId, @RequestBody Text text) {
		textService.updateText(textId, text);
	}

}
