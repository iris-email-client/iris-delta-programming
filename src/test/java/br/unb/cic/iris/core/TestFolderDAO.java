package br.unb.cic.iris.core;

import java.util.Date;
import java.util.List;

import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.exception.DBException;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.persistence.sqlite3.EmailDAO;
import irisdeltaj.simpleaddressbook.br.unb.cic.iris.persistence.sqlite3.FolderDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFolderDAO {

	private FolderDAO dao;
	
	@Before
	public void setUp() throws Exception {
		try {
			dao = FolderDAO.instance();
			
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
	
	@Test
	public void testListMessages(){
		try {
			IrisFolder inbox = dao.findByName(IrisFolder.INBOX);
			EmailDAO.instance().saveMessage(new EmailMessage("from-1", "to-1", "subject-1", "message-1",new Date(System.currentTimeMillis()), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-1", "to-1", "subject-2", "message-2",new Date(), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-2", "to-1", "subject-3", "message-3",new Date(), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-2", "to-1", "subject-4", "message-4",new Date(), inbox));
			
			List<EmailMessage> list = EmailDAO.instance().listMessages(inbox.getId());
			Assert.assertNotNull(list);
			Assert.assertFalse(list.isEmpty());
			for(EmailMessage msg: list){
				System.out.println("MSG: "+msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
