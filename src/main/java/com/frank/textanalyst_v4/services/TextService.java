package com.frank.textanalyst_v4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.frank.textanalyst_v4.domain.Text;
import com.frank.textanalyst_v4.repositories.TextRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextService {

	@Autowired
	private TextRepository textRepository;

	public TextService() {
	}

	public List<Text> getTextbyTitle(String title) {
		List<Text> result = textRepository.findByTitle(title);
		for (int i = 0; i < result.size(); i++) {
			result.get(i).getMetric().update(result.get(i).getText());
		}
		return result;
	}

	public Text getTextById(int id) {
		return textRepository.findById(id).orElseThrow();
	}
	
	public List<String> getAllTitles(boolean isProofed) {
		return textRepository.findTitleByIsProofRead(isProofed);
	}

	public List<Text> getAllProofRead() {
		List<Text> result = textRepository.findByIsProofRead(true);
		return result;
	}
	
	public String findModifyAllDirectSpeech(String text) {
        Pattern pattern = Pattern.compile("[“\"„]([^“\"„]*?)[”\"‟]");
        Matcher matcher = pattern.matcher(text);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(result, "<strong>\"$1\"</strong>");
        }
        // Append the remaining text after the last match
        matcher.appendTail(result);
        return result.toString();
	}
	
	public String addTagsToText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Text> getAllNotProofRead() {
		List<Text> result = textRepository.findByIsProofRead(false);
		return result;
	}

	public Text addText(Text input) {
		if (this.isDublicate(input) == false) {
			// TODO auto generate title when none is given
			// TODO take current Date when none is given
			return textRepository.save(input);
		}
		return null;
	}

	public void updateText(int textId, Text newText) {
		Text existingText = textRepository.findById(textId).orElse(null);
		if (existingText != null) {
			// Update the fields with values from newText
			existingText.setTitle(newText.getTitle());
			existingText.setText(newText.getText());
			existingText.setdateCreated(newText.getdateCreated());
			existingText.setRating(newText.getRating());
			existingText.setSummary(newText.getSummary());
			existingText.setVoiced(newText.isVoiced());
			existingText.setProofRead(newText.isProofRead());
			textRepository.save(existingText);
		} else {
			throw new IllegalArgumentException("Text with ID " + textId + " not found.");
		}
	}

	private boolean isDublicate(Text text) {
		// TODO
		return false;
	}

	public List<Text> saveFromFileSystem(String path, boolean isProofed) {
		LinkedList<Text> results = new LinkedList<>();
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				StringBuilder content = new StringBuilder();
				try {
					BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "Cp1252"));
					String line;
					while ((line = buffer.readLine()) != null) {
						content.append(line).append("\n");
					}
					String title = file.getName().replace(".txt", "");
					BasicFileAttributes attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
					if (content.isEmpty() == false) {
						Text t = new Text(title, content.toString(), new Date(attr.lastModifiedTime().toMillis()),
								isProofed);
						results.add(t);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return (List<Text>) textRepository.saveAll(results);
	}



}
