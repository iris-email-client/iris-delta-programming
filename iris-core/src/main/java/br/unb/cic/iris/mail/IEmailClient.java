package br.unb.cic.iris.mail;

import java.util.List;

import javax.mail.search.SearchTerm;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.model.EmailMessage;
import br.unb.cic.iris.core.model.IrisFolder;

public interface IEmailClient {

	/**
	 * Send an email message
	 * 
	 * @param message
	 * @throws EmailException
	 */
	public void send(EmailMessage message) throws EmailException;

	public List<IrisFolder> listFolders() throws EmailException;

	public List<EmailMessage> getMessages(String folder, SearchTerm searchTerm)
			throws EmailException;
	
	public List<EmailMessage> getMessages(String folder) throws EmailException;

	// public TransportStrategy getTransportStrategy();
	public List<String> validateEmailMessage(EmailMessage message);

	/**
	 * Retrieves all messages from a given seqnum
	 * 
	 * @param seqnum
	 * @return
	 * @throws EmailException
	 */
	public List<EmailMessage> getMessages(String folder, int seqnum) throws EmailException;

	public List<EmailMessage> getMessages(String folder, int begin, int end) throws EmailException;
	
}
