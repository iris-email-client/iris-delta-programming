package br.unb.cic.iris.command;

import java.util.List;

import br.unb.cic.iris.core.exception.EmailException;

public interface ICommandManager {

	// deve permitir adicao dinamica/estatica (tipo plugins)
	public void addCommand(MailCommand command) throws EmailException;

	public MailCommand getCommand(String commandName) throws EmailException;

	// lista todos os comandos registrados
	public List<MailCommand> listAll();

	public void addCommandListener(CommandListener listener);

	public void reload() throws Exception;
	// TODO outras operacoes

}