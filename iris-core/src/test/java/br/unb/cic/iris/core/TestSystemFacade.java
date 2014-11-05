/*
 * TestSystemFacade.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 18, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.core;

import org.junit.Assert;
import org.junit.Test;

import br.unb.cic.iris.mail.EmailProvider;

/**
 * @author ExceptionHandling
 *
 */
public class TestSystemFacade {

	@Test
	public void testDefaultProvider() {
		EmailProvider provider = SystemFacade.instance().getProvider();
		Assert.assertNotNull(provider);
	}
	
}
