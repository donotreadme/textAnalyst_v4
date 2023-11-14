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
import com.frank.textanalyst_v4.services.TextService;

@RestController
@RequestMapping("text")
public class TextController {

	private final TextService textService;

	@Autowired
	public TextController(TextService textService) {
		this.textService = textService;
	}

	@GetMapping
	public List<Text> getText() {
		List<Text> allTexts = textService.getAllProofRead();
		allTexts.addAll(textService.getAllNotProofRead());
		return allTexts;
	}

	@GetMapping(path = "/by/proofed")
	public List<String> getProofedDepending(@RequestParam(value = "value", required = true) boolean isProofed) {
		return textService.getAllTitles(isProofed);
	}
	
	@GetMapping(path = "/titles")
	public List<String> getTitles(@RequestParam(value = "contain", required = false) String title) {
		return null; // TODO implement
	}

	@GetMapping(path = "/{textId}")
	public Text getTextById(@PathVariable("textId") int id, 
			@RequestParam(value = "highlightDialog", required = false, defaultValue = "false") boolean highlightDialog, 
			@RequestParam(value = "tagText", required = false, defaultValue = "false") boolean tagText) {
		Text result = textService.getTextById(id);
		result.getMetric().update(result.getText());
		if (highlightDialog) {
			result.setText(textService.findModifyAllDirectSpeech(result.getText()));
		}
		if (tagText) {
			result.setText(textService.addTagsToText(result.getText()));
		}
		return result;
	}

	@GetMapping(path = "/by/title")
	public Text getTextByTitle(@RequestParam(value = "title", required = true, defaultValue = "") String title, 
			@RequestParam(value = "highlightDialog", required = false, defaultValue = "false") boolean highlightDialog, 
			@RequestParam(value = "tagText", required = false, defaultValue = "false") boolean tagText) {
		// TODO utilize find by id pipeline, to get the text tagged and highlighted
		if (title != "") {
			Text result = textService.getTextbyTitle(title).get(0); //TODO fix me!
			result.getMetric().update(result.getText());
			if (highlightDialog) {
				result.setText(textService.findModifyAllDirectSpeech(result.getText()));
			}
			if (tagText) {
				result.setText(textService.addTagsToText(result.getText()));
			}
			return result;
		} else {
			throw new IllegalArgumentException("The argument can not be null");
		}

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
