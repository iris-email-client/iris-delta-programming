/*
 * ConsoleSendMessageCommand.java
 * ---------------------------------
 *  version: 0.0.1
 *  date: Sep 8, 2014
 *  author: rbonifacio
 *  list of changes: (none) 
 */
package br.unb.cic.iris.command.console;

import java.net.URLClassLoader;
import java.util.Scanner;

import br.unb.cic.iris.command.AbstractMailCommand;
import br.unb.cic.iris.command.MailCommand;
import br.unb.cic.iris.reflect.JarFileLoader;

/**
 * 
 * @author rbonifacio
 */
public class LoadCommand extends AbstractMailCommand {
	public static final String COMMAND_LOAD = "load";

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.unb.cic.iris.command.MailCommand#explain()
	 */
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_LOAD,
				"Testando carga dinamica de command");
	}

	@Override
	public String getCommandName() {
		return COMMAND_LOAD;
	}

	@Override
	public void handleExecute() {
		showClassPath();

		Scanner sc = new Scanner(System.in);

		System.out.println("Jar file path: ");
		String jarPath = sc.nextLine();

		try (URLClassLoader loader = (URLClassLoader) getClass()
				.getClassLoader();
				JarFileLoader l = new JarFileLoader(loader.getURLs())) {

			l.addFile(jarPath);

			System.setProperty(
					"java.class.path",
					System.getProperty("java.class.path")
							+ System.getProperty("path.separator") + jarPath);

			showClassPath();

			// TODO escanear o jar para encontrar commands

			// l.addURL(new URL("file:C:/Temp/xyz.jar"));
			// apenas teste por enquanto ...
			Class c = l
					.loadClass("br.unb.cic.iris.teste.ConsoleHelloWorldCommand");
			System.out.println(c.getName());

			ConsoleCommandManager.singleton().addCommand(
					(MailCommand) c.newInstance());

			// System.out.println("\n\n\n\n\nreloading commands ...");

			// ConsoleCommandManager.singleton().reload();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void showClassPath() {
		String classpath = System.getProperty("java.class.path");
		System.out.println("classpath=" + classpath);
	}
}
