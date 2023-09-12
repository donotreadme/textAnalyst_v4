package com.frank.textanalyst_v4.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class TextMetricTest {

	private TextMetric metric; 
	@BeforeEach
	void setUp() throws Exception {
		metric = new TextMetric("This is a test! \nIt has multiple lines. And sentences\n\nEven paragraphs");
	}

	@org.junit.jupiter.api.Test
	void testTextMetrics() {
		fail("Not yet implemented");
	}

	@org.junit.jupiter.api.Test
	void testGetNumberOfParagraphs() {
		fail("Not yet implemented");
	}

	@org.junit.jupiter.api.Test
	void testGetNumberOfSentences() {
		fail("Not yet implemented");
	}

	@org.junit.jupiter.api.Test
	void testGetNumberOfWords() {
		fail("");
	}

	@org.junit.jupiter.api.Test
	void testGetNumberOfCharacters() {
		fail("Not yet implemented");
	}

	@org.junit.jupiter.api.Test
	void testGetNumberOfConversations() {
		fail("Not yet implemented");
	}

}
