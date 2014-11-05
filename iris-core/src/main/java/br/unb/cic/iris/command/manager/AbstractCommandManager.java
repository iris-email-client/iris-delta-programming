package br.unb.cic.iris.command.manager;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Set;

import br.unb.cic.iris.command.MailCommand;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.reflect.ClassFinder;

public abstract class AbstractCommandManager extends BaseCommandManager {

	// disponibilizar esse metodo para os deltas se registrarem,
	// e outros (possiveis) "plugins" (via merge de codigo)
	// e acesso atraves do singleton
	// protected abstract void initialize();

	public AbstractCommandManager() {
		try {
			loadClasspathCommands();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void reload() throws Exception {
		loadClasspathCommands();
	}

	private void loadClasspathCommands() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, EmailException {
		System.out.println("Scanning commands ...");

		// commandMap = Collections.synchronizedMap(new HashMap<>());

		ClassFinder classFinder = new ClassFinder(MailCommand.class);
		Set classesFound = classFinder.getClasses();
		for (Iterator it = classesFound.iterator(); it.hasNext();) {
			String clazzName = it.next().toString();

			Class<?> c = Class.forName(clazzName);
			if (!c.isInterface() && !Modifier.isAbstract(c.getModifiers())) {
				MailCommand command = (MailCommand) c.newInstance();
				addCommand(command);
			}
		}

		System.out.println("Total commands found: " + listAll().size());
	}

}
