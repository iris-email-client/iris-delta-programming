/*
 * ConsoleHelpCommand
 * -------------------------------------------
 *  version: 0.0.1
 *  date: September, 2014
 *  author(s): rbonifacio
 *  
 *  changes: (none)
 */
package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;
import br.unb.cic.iris.command.AbstractMailCommand;
import br.unb.cic.iris.command.MailCommand;

/**
 * A simple mail command that explains how to use the other commands.
 * 
 * @author rbonifacio
 */
public class ConsoleHelpCommand extends AbstractMailCommand {
	static final String COMMAND_HELP = "help";

	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_HELP,
				message("command.help.explain"));
	}

	@Override
	public void handleExecute() {
		for (MailCommand c : ConsoleCommandManager.singleton().listAll()) {
			c.explain();
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_HELP;
	}

}
