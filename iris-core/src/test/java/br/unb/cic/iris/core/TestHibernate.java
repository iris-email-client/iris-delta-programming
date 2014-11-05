package br.unb.cic.iris.core;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import br.unb.cic.iris.util.HibernateUtil;

public class TestHibernate {

	@Test
	public void testCreateHibernateSession() {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Assert.assertTrue(true);
			session.close();
		}
		catch(Throwable t) {
			t.printStackTrace();
			Assert.fail("Error building hibernate session");
		}
	}
}
