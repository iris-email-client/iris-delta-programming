/*
 * Element
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 6, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * An abstract class that represents both folders and elements. Note that this class is empty, it is only useful to
 * provide an hierarchy that comprises both folders and elements.
 * 
 * In addition, it is not possible to instantiate an object of this class.
 * 
 * @author rbonifacio
 */
@Entity
@Table(name = "TB_FOLDER_CONTENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FolderContent {

	public FolderContent() {
		this(null);
	}

	public FolderContent(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
