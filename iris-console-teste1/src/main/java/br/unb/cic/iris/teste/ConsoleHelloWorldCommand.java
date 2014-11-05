package br.unb.cic.iris.teste;

import br.unb.cic.iris.command.AbstractMailCommand;

public class ConsoleHelloWorldCommand extends AbstractMailCommand {
	public static final String COMMAND_HELLO = "hello";

	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n",COMMAND_HELLO,"Exemplo de command adicionado dinamicamente");
	}

	@Override
	public void handleExecute() {
		System.out.println("Hello World!!");
	}

	@Override
	public String getCommandName() {
		return COMMAND_HELLO;
	}

}
