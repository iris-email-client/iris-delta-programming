/*
 * Email
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A class that represents an email message.
 * 
 * @author ExceptionHandling
 */
@Entity
@Table(name = "TB_MESSAGE")
public class EmailMessage extends FolderContent {
	
	@Column(name="MSG_FROM")
	private String from;
	
	@Column(name="RECIPIENT")
	private String to;
	
	@Column(name="CC_RECIPIENT")
	private String cc;
	
	@Column(name="BCC_RECIPIENT")
	private String bcc;
	
	@Column(name="SUBJECT")
	private String subject;
	
	@Column(name="MESSAGE_CONTENT")
	private String message;
	
	@Column(name="DATE")
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOLDER_ID", nullable = false)
	private IrisFolder folder;
	

	public EmailMessage() {
	}

	public EmailMessage(String to, String subject, String message) {
		this(null, to, subject, message);
	}

	public EmailMessage(String from, String to, String subject, String message) {
		this(from, to, null, null, subject, message);
	}

	public EmailMessage(String from, String to, String cc, String bcc,
			String subject, String message) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.message = message;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public IrisFolder getFolder() {
		return folder;
	}

	public void setFolder(IrisFolder folder) {
		this.folder = folder;
	}

}
