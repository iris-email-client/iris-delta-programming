package br.unb.cic.iris.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {
	private static final String[] INVALID_EMAILS = new String[] { "mkyong", "mkyong@.com.my",
		"mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
		".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
		"mkyong..2002@gmail.com", "mkyong.@gmail.com",
		"mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };


	private static final String[] VALID_EMAILS = new String[] { "mkyong@yahoo.com",
		"mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
		"mkyong111@mkyong.com", "mkyong-100@mkyong.net",
		"mkyong.100@mkyong.com.au", "mkyong@1.com",
		"mkyong@gmail.com.com", "mkyong+100@gmail.com",
		"mkyong-100@yahoo-test.com" };
	
	
	private EmailValidator emailValidator;
	 
	@Before
	public void initData() {
		emailValidator = new EmailValidator();
	}
 
	@Test()
	public void ValidEmailTest() {
		for (String temp : VALID_EMAILS) {
			boolean valid = emailValidator.validate(temp);
			System.out.println("Email is valid : " + temp + " , " + valid);
			Assert.assertEquals(valid, true);
		}
	}
 
	@Test()
	public void InValidEmailTest() {
		for (String temp : INVALID_EMAILS) {
			boolean valid = emailValidator.validate(temp);
			System.out.println("Email is valid : " + temp + " , " + valid);
			Assert.assertEquals(valid, false);
		}
	}
}
