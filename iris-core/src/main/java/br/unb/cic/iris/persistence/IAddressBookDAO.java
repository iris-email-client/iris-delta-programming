package br.unb.cic.iris.persistence;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.core.model.AddressBookEntry;

/**
 * A DAO for address books. 
 * 
 * @author modularidade
 *
 */
public interface IAddressBookDAO {

	public void save(AddressBookEntry entry) throws DBException;
	
	public AddressBookEntry find(String nick) throws DBException;
	
	public void delete(String nick) throws DBException;
}
