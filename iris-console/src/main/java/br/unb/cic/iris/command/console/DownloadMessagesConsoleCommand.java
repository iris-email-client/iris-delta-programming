package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;

import java.util.List;

import br.unb.cic.iris.command.AbstractMailCommand;
import br.unb.cic.iris.core.SystemFacade;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.model.EmailMessage;
import br.unb.cic.iris.core.model.IrisFolder;
import br.unb.cic.iris.persistence.sqlite3.EmailDAO;

public class DownloadMessagesConsoleCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "download";

	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME, message("command.download.explain"));
	}

	@Override
	public void handleExecute() throws EmailException {
		String folder = IrisFolder.INBOX;
		if (validParameters()) {
			folder = parameters[0];
		}

		System.out.println("Downloading messages from/to folder: " + folder);

		SystemFacade.instance().downloadMessages(folder);

		//just to test ... TODO remove for production
		List<EmailMessage> list = EmailDAO.instance().findAll();
		for(EmailMessage msg: list){
			System.out.println("---> "+msg.getFrom()+" -- "+msg.getSubject());
		}

	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
