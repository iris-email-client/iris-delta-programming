package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.unb.cic.iris.command.AbstractMailCommand;
import br.unb.cic.iris.core.SystemFacade;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.model.EmailMessage;

public class ConsoleGetMessagesCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "get";

	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME, message("command.list.folders.explain"));
	}

	@Override
	public void handleExecute() throws EmailException {
		if (validParameters()) {
			String folder = parameters[0];
			System.out.println("Retrieving messages from folder: " + folder);

			// TODO tratar parametros --> parse para Flags/SearchTerm
			DateFormat formatter = new SimpleDateFormat("dd/MMM/yy 'at' HH:mm");
			List<EmailMessage> messages = SystemFacade.instance().getMessages(folder);
			System.out.println("Total messages: " + messages.size());
			for (EmailMessage msg : messages) {
				System.out.printf("%s - %s \t- %s%n", formatter.format(msg.getDate()), msg.getFrom(), msg.getSubject());
			}
		} else {
			// TODO
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
