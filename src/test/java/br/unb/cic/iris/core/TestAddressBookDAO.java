package br.unb.cic.iris.core;

import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.AddressBookEntry;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.FolderContent;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.persistence.sqlite3.AddressBookDAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAddressBookDAO {

	private static final String USER_TEST = "user-test";
	private AddressBookDAO dao;
	
	@Before
	public void setUp() throws Exception {
		try {
			dao = AddressBookDAO.instance();
			
			AddressBookEntry entry = dao.find(USER_TEST);
			
			if(entry != null) {
				dao.delete(entry.getNick());
			}
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	

	
	@Test
	public void save() {
		try {
			AddressBookEntry entry = dao.find(USER_TEST);
			Assert.assertNull(entry);
			dao.save(new AddressBookEntry(USER_TEST , "br.unb.cic.iris@gmail.com"));
			entry = dao.find(USER_TEST);
			Assert.assertNotNull(entry);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing AddressBookDao.save()");
		}
	}
	
	/*@Test
	public void testNewSessionFactory(){
		SessionFactory sessionFactory = new AnnotationConfiguration()
        .addPackage("irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model") //the fully qualified package name
        //.addAnnotatedClass(AddressBookEntry.class)
        //.addAnnotatedClass(FolderContent.class)
        //.addAnnotatedClass(IrisFolder.class)
        //.addAnnotatedClass(EmailMessage.class)
       // .addResource("test/animals/orm.xml")
        .configure()
        .buildSessionFactory();
		String FIND_BY_NICK_NAME = "FROM AddressBookEntry a " +
				"where a.nick = :pNick";
		sessionFactory.openSession().createQuery(FIND_BY_NICK_NAME).setParameter("pNick", USER_TEST).list();
	}*/
}
