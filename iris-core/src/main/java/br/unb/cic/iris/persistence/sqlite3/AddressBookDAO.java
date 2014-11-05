package br.unb.cic.iris.persistence.sqlite3;

import java.util.List;

import br.unb.cic.iris.core.exception.DBException;
import br.unb.cic.iris.core.model.AddressBookEntry;
import br.unb.cic.iris.persistence.IAddressBookDAO;

public final class AddressBookDAO extends AbstractDAO<AddressBookEntry> implements IAddressBookDAO {
		
	private static final String FIND_BY_NICK_NAME = 
			"FROM AddressBookEntry a "
			+ "where a.nick = :pNick";
	
	
	private static AddressBookDAO instance; 
	
	private AddressBookDAO() { } 
	
	public static AddressBookDAO instance() {
		if(instance == null) {
			instance = new AddressBookDAO();
		}
		return instance;
	}
	
	
	@Override
	public void save(AddressBookEntry entry) throws DBException{
		super.saveOrUpdate(entry);
	}

	
	@Override
	public AddressBookEntry find(String nick) throws DBException {
		try {
			//session = HibernateUtil.getSessionFactory().openSession();
			startSession();
			List<AddressBookEntry> entries = session.createQuery(FIND_BY_NICK_NAME).setParameter("pNick", nick).list();
			
			if(entries != null && entries.size() == 1) {
				return entries.get(0);
			}
			return null;
		} catch(Exception e) {
			throw new DBException("could not save the address book entry", e);	
		} finally {
			closeSession();
		}
	}

	@Override
	public void delete(String nick) throws DBException {
		AddressBookEntry entry = find(nick);
		if(entry != null){
			super.delete(entry);
		}
	}

}
