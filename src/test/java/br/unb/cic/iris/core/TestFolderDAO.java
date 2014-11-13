package br.unb.cic.iris.core;

import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.exception.DBException;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.persistence.sqlite3.FolderDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFolderDAO {

	private FolderDAO dao;
	
	@Before
	public void setUp() throws Exception {
		try {
			dao = new FolderDAO();
			
			IrisFolder inbox = dao.findByName(IrisFolder.INBOX);
			if(inbox == null){
				inbox = new IrisFolder(IrisFolder.INBOX);
				dao.saveOrUpdate(inbox);
			}
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	
	@Test
	public void testFindByName(){
		try {
			IrisFolder findFolder = dao.findByName(IrisFolder.INBOX);
			Assert.assertNotNull(findFolder);
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void save() {
		try {
			IrisFolder folder = new IrisFolder(IrisFolder.OUTBOX);
			
			dao.saveOrUpdate(folder);
			
			IrisFolder findFolder = dao.findByName(IrisFolder.OUTBOX);
			Assert.assertNotNull(findFolder);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing FolderDAO.saveOrUpdate()");
		}
	}
	
}
