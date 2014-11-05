package br.unb.cic.iris.util;

import java.util.regex.Pattern;

public class EmailValidator {
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
 
	/**
	 * Validate email with regular expression
	 * 
	 * @param email
	 *            email for validation
	 * @return true valid email, false invalid email
	 */
	public static boolean validate(final String email) {
		return pattern.matcher(email).matches();
	}
}
