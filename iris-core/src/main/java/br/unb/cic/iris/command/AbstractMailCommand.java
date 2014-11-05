package br.unb.cic.iris.command;

import static br.unb.cic.iris.i18n.Message.message;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.exception.EmailMessageValidationException;
import br.unb.cic.iris.core.exception.EmailUncheckedException;

public abstract class AbstractMailCommand implements MailCommand {
	protected String[] parameters;

	@Override
	public void setParameters(String... parameters) {
		this.parameters = parameters;
	}

	protected boolean validParameters() {
		return parameters != null && parameters.length > 0;
	}

	protected abstract void handleExecute() throws EmailException;

	public void execute() {
		try {
			handleExecute();
		} catch (EmailUncheckedException eux) {
			System.err.printf("%s: %s", message("error"), eux.getLocalizedMessage());
		} catch (EmailMessageValidationException emvx) {
			System.err.println(message("error.validation"));
			for (String msg : emvx.getMessages()) {
				System.err.println(" - " + msg);
			}
		} catch (EmailException ex) {
			System.err.printf("%s: %s", message("error"), ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
