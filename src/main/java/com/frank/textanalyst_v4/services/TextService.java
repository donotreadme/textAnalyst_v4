package com.frank.textanalyst_v4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.frank.textanalyst_v4.domain.Text;
import com.frank.textanalyst_v4.repositories.TextRepository;

@Service
public class TextService {

	@Autowired
	private TextRepository textRepository;

	public TextService() {
	}

	public Text getTaggedText(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Text> getTextbyTitle(String title) {
		List<Text> result = textRepository.findByTitle(title);
		return result;
	}

	public Text getTextById(int id) {
		return textRepository.findById(id).orElseThrow();
	}

	public List<Text> getAllProofRead() {
		return textRepository.findByIsProofRead(true);
	}

	public List<Text> getAllNotProofRead() {
		return textRepository.findByIsProofRead(false);
	}

	public Text addText(Text input) {
		if (this.isDublicate(input) == false) {
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
					BufferedReader buffer = new BufferedReader(new FileReader(file.getAbsoluteFile()));
					String line;
					while ((line = buffer.readLine()) != null) {
						content.append(line).append("\n");
					}
					String title = file.getName().replace(".txt", "");
					BasicFileAttributes attr = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
					if (!content.isEmpty()) {
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
