package br.unb.cic.iris.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class that represents an address book entry. 
 * 
 * @author modularidade
 */
@Entity
@Table(name="TB_ADDRESS_BOOK")
public class AddressBookEntry {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String nick;
	
	@Column
	private String address;
	
	public AddressBookEntry() {}
	
	public AddressBookEntry(String nick, String address) {
		this.nick = nick;
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
