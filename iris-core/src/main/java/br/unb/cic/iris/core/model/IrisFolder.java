/*
 * IrisFolder
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A domain class that represents email folders.
 * 
 * @author rbonifacio
 */
@Entity
@Table(name = "TB_FOLDER")
public class IrisFolder extends FolderContent {
	public static final String INBOX = "INBOX";
	public static final String OUTBOX = "OUTBOX";
	
	@Column(name="NAME", unique=true)
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FolderContent> contents;

	public IrisFolder() {
		this(null,"");
	}
	
	public IrisFolder(String name) {
		this(null,name);
	}

	public IrisFolder(Integer id, String name) {
		super(id);
		this.name = name;
		contents = new ArrayList<FolderContent>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addElement(FolderContent e) {
		contents.add(e);
	}

	public List<FolderContent> getContents() {
		return contents;
	}
}
