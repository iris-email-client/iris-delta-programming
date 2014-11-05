/*
 * CommandNotFoundException
 * -------------------------------------
 *  version: 0.0.1
 *  
 *  date: September, 2014
 *  
 *  authors: rbonifacio
 *  
 *  List of changes: (none)
 */
package br.unb.cic.iris.command;

/**
 * Exception that must be thrown when the interpreter could not execute (find) a
 * user command.
 * 
 * @author ExceptionHandling
 */
public class CommandNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// TODO: mover para package de excecoes?
	public CommandNotFoundException() {
	}

	public CommandNotFoundException(String msg) {
		super(msg);
	}

	public CommandNotFoundException(Throwable cause) {
		super(cause);
	}
}
