package com.frank.textanalyst_v4.domain;

public class TextMetric {

	private int numberOfParagraphs;
	private int numberOfSentences;
	private int numberOfWords;
	private int numberOfCharacters;
	private int numberOfConversations;

	public TextMetric(String text) {
		this.update(text);
	}

	public void update(String text) {
		if (!(text == null || text.trim().isEmpty())) { // Check if the text is empty, if not get metrics
			this.numberOfParagraphs = this.calculateNumberOfParagraphs(text);
			this.numberOfSentences = this.calculateNumberOfSentences(text);
			this.numberOfWords = this.calculateNumberOfWords(text);
			this.numberOfCharacters = this.calculateNumberOfCharacters(text);
			this.numberOfConversations = this.calculateNumberOfConversations(text);
		}else {
			this.numberOfParagraphs = 0;
			this.numberOfSentences = 0;
			this.numberOfWords = 0;
			this.numberOfCharacters = 0;
			this.numberOfConversations = 0;
		}
	}

	public int calculateNumberOfParagraphs(String text) {
		String[] paragraphs = text.split("\\s*\\n\\s*\\n"); // Split text by empty lines
		return paragraphs.length;
	}

	public int calculateNumberOfSentences(String text) {
		String[] sentences = text.split("[.!?]+"); // Split text by sentence-ending punctuation
		return sentences.length;
	}

	public int calculateNumberOfWords(String text) {
		String[] words = text.split("\\s+");
		return words.length;
	}

	public int calculateNumberOfCharacters(String text) {
		String[] chars = text.split("");
		return chars.length;
	}

	public int calculateNumberOfConversations(String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfParagraphs() {
		return numberOfParagraphs;
	}

	public int getNumberOfSentences() {
		return numberOfSentences;
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}

	public int getNumberOfCharacters() {
		return numberOfCharacters;
	}

	public int getNumberOfConversations() {
		return numberOfConversations;
	}

}
