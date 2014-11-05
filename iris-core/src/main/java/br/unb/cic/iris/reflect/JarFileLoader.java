package br.unb.cic.iris.reflect;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

//preparando para carregar "plugins" dinamicamente
public class JarFileLoader extends URLClassLoader {
	public JarFileLoader(URL[] urls) {
		super(urls);
	}

	public void addFile(String path) throws MalformedURLException {
		String urlPath = "jar:file://" + path + "!/";
		addURL(new URL(urlPath));
		/*
		 * TODO URLClassLoader loader =
		 * (URLClassLoader)ClassLoader.getSystemClassLoader(); JarFileLoader l =
		 * new JarFileLoader(loader.getURLs()); l.addURL(new
		 * URL("file:C:/Temp/xyz.jar")); Class c =
		 * l.loadClass("package.classname"); System.out.println(c.getName());
		 */
	}

	public static void main(String args[]) {
		try {
			System.out.println("First attempt...");
			Class.forName("org.postgresql.Driver");
		} catch (Exception ex) {
			System.out.println("Failed.");
		}

		try {
			URL urls[] = {};

			JarFileLoader cl = new JarFileLoader(urls);
			cl.addFile("/home/pedro/desenvolvimento/repositorio/m2/postgresql/postgresql/9.1-901.jdbc4/postgresql-9.1-901.jdbc4.jar");
			System.out.println("Second attempt...");
			cl.loadClass("org.postgresql.Driver");
			System.out.println("Success!");
		} catch (Exception ex) {
			System.out.println("Failed.");
			ex.printStackTrace();
		}
	}
}
