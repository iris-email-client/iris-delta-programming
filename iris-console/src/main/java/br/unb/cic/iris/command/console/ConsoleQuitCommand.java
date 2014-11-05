package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;
import br.unb.cic.iris.command.AbstractMailCommand;

public class ConsoleQuitCommand extends AbstractMailCommand {
	public static final String COMMAND_QUIT = "quit";

	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_QUIT,
				message("command.quit.explain"));
	}

	@Override
	public void handleExecute() {
		System.out.println(message("quit"));
		System.exit(0);
	}

	@Override
	public String getCommandName() {
		return COMMAND_QUIT;
	}

}
