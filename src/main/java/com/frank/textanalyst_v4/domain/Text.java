package com.frank.textanalyst_v4.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "texts")
public class Text {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String text;
	private Date dateCreated;
	@Transient
	private TextMetric metric = new TextMetric("");
	@Transient
	private int rating = 0;
	private String summary;
	private boolean isVoiced;
	private boolean isProofRead;
	private boolean markedForDeletion;

	public Text() {
	}

	public Text(int id, String title, String text, Date dateCreated, int rating, String summary, boolean isVoiced,
			boolean isProofRead, boolean markedForDeletion) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.dateCreated = dateCreated;
		this.metric = new TextMetric(text);
		this.rating = rating;
		this.summary = summary;
		this.isVoiced = isVoiced;
		this.markedForDeletion = markedForDeletion;
	}

	public Text(String title, String text, Date dateCreated, boolean isProofRead) {
		super();
		this.title = title;
		this.text = text;
		this.dateCreated = dateCreated;
		this.metric = new TextMetric(text);
		this.isProofRead = isProofRead;
		this.summary = "";
		this.isVoiced = false;
	}

	public Text(String text) {
		super();
		this.text = text;
		this.metric = new TextMetric(text);
	}

	public Text(String title, String text) {
		super();
		this.text = text;
		this.title = title;
		this.metric = new TextMetric(text);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getdateCreated() {
		return dateCreated;
	}

	public void setdateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public TextMetric getMetric() {
		return metric;
	}

	public void setMetric(TextMetric metric) {
		this.metric = metric;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getId() {
		return id;
	}

	public boolean isVoiced() {
		return isVoiced;
	}

	public void setVoiced(boolean isVoiced) {
		this.isVoiced = isVoiced;
	}

	public boolean isProofRead() {
		return isProofRead;
	}

	public void setProofRead(boolean isProofRead) {
		this.isProofRead = isProofRead;
	}

	public boolean isMarkedForDeletion() {
		return markedForDeletion;
	}

	public void setMarkedForDeletion(boolean markedForDeletion) {
		this.markedForDeletion = markedForDeletion;
	}

}
