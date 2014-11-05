package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;
import static br.unb.cic.iris.util.StringUtil.notEmpty;
import br.unb.cic.iris.command.MailCommand;
import br.unb.cic.iris.command.manager.AbstractCommandManager;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.exception.EmailMessageValidationException;
import br.unb.cic.iris.core.exception.EmailUncheckedException;

public class ConsoleCommandManager extends AbstractCommandManager {
	private static ConsoleCommandManager singleton = new ConsoleCommandManager();

	private ConsoleCommandManager() {
	}

	public static ConsoleCommandManager singleton() {
		return singleton;
	}

	public static void run(String cmd) {
		ConsoleCommandManager.singleton().runCommand(cmd);
	}

	public void runCommand(String cmd) {
		if (notEmpty(cmd)) {
			try {
				MailCommand command = createCommand(cmd.trim());
				command.execute();
			} catch (EmailUncheckedException eux) {
				System.err.printf("%s: %s", message("error"), eux.getLocalizedMessage());
			} catch (EmailMessageValidationException emvx) {
				System.err.println(message("error.validation"));
				for(String msg: emvx.getMessages()){
					System.err.println(" - "+msg);
				}
			} catch (EmailException ex) {
				System.err.printf("%s: %s", message("error"), ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable t){
				t.printStackTrace();
			}
		}
	}

	private MailCommand createCommand(String cmd) throws EmailException {
		String[] split = cmd.split(" ");
		MailCommand command = null;
		if (split.length > 1) {
			command = getCommand(split[0].trim());
			command.setParameters(getParameters(split));
		} else {
			command = getCommand(cmd);
		}
		return command;
	}

	private static String[] getParameters(String[] split) {
		String[] parameters = new String[split.length - 1];
		for (int i = 1; i < split.length; i++) {
			parameters[i - 1] = split[i].trim();
		}
		return parameters;
	}

	@Override
	protected void handleAddCommand(MailCommand command) {
		// empty, no need to specific actions
	}

}
