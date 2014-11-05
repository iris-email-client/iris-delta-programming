package br.unb.cic.iris.command.console;

import static br.unb.cic.iris.i18n.Message.message;

import java.util.Scanner;

import br.unb.cic.iris.command.AbstractMailCommand;
import br.unb.cic.iris.core.SystemFacade;
import br.unb.cic.iris.mail.EmailProvider;
import br.unb.cic.iris.mail.provider.ProviderManager;

public class ConsoleConnectCommand extends AbstractMailCommand {
	public static final String COMMAND_NAME = "connect";

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unb.cic.iris.command.MailCommand#explain()
	 */
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME,
				message("command.connect.explain"));
	}

	@Override
	public void handleExecute() {
		Scanner sc = new Scanner(System.in);

		String providerStr = read(sc, "command.connect.label.provider");
		EmailProvider provider = ProviderManager.instance().getProvider(
				providerStr);
		System.out.println("Prodiver recovered: " + provider.getName());
		// TODO validar provider antes de ler o resto

		String username = read(sc, "command.connect.label.username");
		String password = read(sc, "command.connect.label.password");

		provider.setUsername(username);
		provider.setPassword(password);

		SystemFacade.instance().connect(provider);
	}

	private static String read(Scanner sc, String question) {
		askQuestion(question);
		return sc.nextLine();
	}

	private static void askQuestion(String message) {
		System.out.printf("%s: ", message(message));
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}

}
