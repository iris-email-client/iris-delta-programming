package br.unb.cic.iris.command;

import br.unb.cic.iris.core.SystemFacade;
import br.unb.cic.iris.core.model.EmailMessage;

public abstract class SendMessageCommand extends AbstractMailCommand {
	protected static final String EXPLAIN_I18n = "command.send.explain";

	protected abstract EmailMessage createMessage();

	@Override
	public void execute() {
		EmailMessage m = createMessage();
		try {
			SystemFacade.instance().send(m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
