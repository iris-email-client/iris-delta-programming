package br.unb.cic.iris.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unb.cic.iris.core.model.IrisFolder;
import br.unb.cic.iris.persistence.sqlite3.FolderDAO;

public class TestFolderDAO {

	private static final String DEFAULT_NAME = "test-folder";
	private FolderDAO dao;
	
	@Before
	public void setUp() throws Exception {
		try {
			dao = new FolderDAO();
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	
	@Test
	public void save() {
		try {
			IrisFolder folder = new IrisFolder(DEFAULT_NAME);
			dao.saveOrUpdate(folder);
			
			List<IrisFolder> all = dao.findAll();
			for(IrisFolder f: all){
				System.out.println("FOLDER: "+f.getName());
			}
			Assert.assertTrue(!all.isEmpty());
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing TestFolderDAO.save()");
		}
	}
}
