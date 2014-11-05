/* 
 *  MainProgram
 *  
 *  version: 0.0.1
 *  
 *  data: September, 2014
 *  
 *  author: rbonifacio@cic.unb.br
 */
package br.unb.cic.iris;

import static br.unb.cic.iris.i18n.Message.message;

import java.util.Scanner;

import br.unb.cic.iris.command.console.ConsoleCommandManager;
import br.unb.cic.iris.core.SystemFacade;

public class MainProgram {

	Scanner sc;

	public MainProgram() {
		sc = new Scanner(System.in);
	}

	public static void main(String args[]) {
		MainProgram m = new MainProgram();
		//eager loading/starting the system
		SystemFacade.instance();
		ConsoleCommandManager.singleton().runCommand("");
		
		m.mainMenu();
		m.readCommand();
	}

	private void mainMenu() {
		System.out.println(message("interpreter"));
		System.out.println(message("version"));
		System.out.println(message("help"));
	}

	private void readCommand() {
		try {
			System.out.print(message("prompt"));
			String cmd = sc.nextLine().trim();
			ConsoleCommandManager.singleton().runCommand(cmd);
		} catch (RuntimeException e) {
			System.err.println(message("error") + e.getLocalizedMessage());
		}
		readCommand();
	}

}
