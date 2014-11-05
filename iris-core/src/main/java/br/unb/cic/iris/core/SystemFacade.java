package br.unb.cic.iris.core;

import static br.unb.cic.iris.core.model.Status.CONNECTED;
import static br.unb.cic.iris.core.model.Status.NOT_CONNECTED;
import static br.unb.cic.iris.i18n.Message.message;

import java.util.Date;
import java.util.List;

import javax.mail.Flags;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.exception.EmailUncheckedException;
import br.unb.cic.iris.core.model.AddressBookEntry;
import br.unb.cic.iris.core.model.EmailMessage;
import br.unb.cic.iris.core.model.IrisFolder;
import br.unb.cic.iris.core.model.Status;
import br.unb.cic.iris.mail.EmailClient;
import br.unb.cic.iris.mail.EmailProvider;
import br.unb.cic.iris.mail.IEmailClient;
import br.unb.cic.iris.mail.provider.DefaultProvider;
import br.unb.cic.iris.mail.provider.ProviderManager;
import br.unb.cic.iris.persistence.IEmailDAO;
import br.unb.cic.iris.persistence.sqlite3.AddressBookDAO;
import br.unb.cic.iris.persistence.sqlite3.EmailDAO;
import br.unb.cic.iris.persistence.sqlite3.FolderDAO;

public final class SystemFacade {
	private static final SystemFacade instance = new SystemFacade();

	private IEmailClient client;
	private EmailProvider provider;

	private Status status = NOT_CONNECTED;

	private SystemFacade() {
		// load config file
		Configuration config = new Configuration();
		// create a default provider based on properties loaded
		provider = new DefaultProvider(config.getProperties());
		// register the default provider
		ProviderManager.instance().addProvider(provider);
		// initiate connection
		connect(provider);
	}

	public static SystemFacade instance() {
		return instance;
	}

	public void connect(EmailProvider provider) {
		setStatus(NOT_CONNECTED);
		this.provider = provider;
		client = new EmailClient(provider);
		setStatus(CONNECTED);
	}

	public void send(EmailMessage message) throws EmailException {
		verifyConnection();
		client.send(message);
		
		IEmailDAO dao = EmailDAO.instance();
		IrisFolder folderOutbox = new FolderDAO().findByName(IrisFolder.OUTBOX);
		message.setFolder(folderOutbox);
		dao.saveMessage(message);
	}

	public List<IrisFolder> listFolders() throws EmailException {
		verifyConnection();
		return client.listFolders();
	}
	
	public void downloadMessages(String folder) throws EmailException {
		verifyConnection();
		
		SearchTerm searchTerm = null;
		IEmailDAO dao = EmailDAO.instance();
		
		Date lastMessageReceived = dao.lastMessageReceived();
		if(lastMessageReceived != null){
			// search for newer messages (relative to lastMessageReceived)
			searchTerm = new ReceivedDateTerm(ComparisonTerm.GT, lastMessageReceived);
		}
		
		//retrieve messages from server
		List<EmailMessage> messages = client.getMessages(folder, searchTerm);
		
		try{
		//persist messages
		for(EmailMessage message: messages) {
			IrisFolder folderEntity = new FolderDAO().findByName(folder);
			//folderEntity.addElement(message);
			message.setFolder(folderEntity);
			dao.saveMessage(message);
		}
		}catch(Throwable t){
			t.printStackTrace();
		}
	}
	
	public List<EmailMessage> getMessages(String folder) throws EmailException {
		//TODO ler as mensagens do banco de dados
		
		
		// apenas para testar ... retorna as mensagens recentes e nao lidas
		Flags seen = new Flags(Flags.Flag.SEEN);
		FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

		Flags recent = new Flags(Flags.Flag.RECENT);
		FlagTerm recentFlagTerm = new FlagTerm(recent, true);

		SearchTerm searchTerm = new AndTerm(unseenFlagTerm, recentFlagTerm);

		// return client.getMessages(folder, searchTerm);
		return client.getMessages(folder, unseenFlagTerm);
		// return client.getMessages(folder, null);
	}

	private void verifyConnection() {
		if (!isConnected()) {
			throw new EmailUncheckedException(message("error.not.connected"));
		}
	}

	public boolean isConnected() {
		return CONNECTED == getStatus();
	}

	private void setStatus(Status status) {
		this.status = status;
		// notify listerners ...
	}

	public Status getStatus() {
		return status;
	}

	public EmailProvider getProvider() {
		return provider;
		// TODO clonar pq nao tem como criar um Properties imutavel (nao tem via
		// api padrao)
		/*
		 * try { return provider.clone(); } catch (CloneNotSupportedException e)
		 * { //TODO mensagem throw new EmailUncheckedException("", e); }
		 */
	}

	public void addAddressBookEntry(String name, String email) throws EmailException {
		AddressBookDAO dao = AddressBookDAO.instance();
		dao.save(new AddressBookEntry(name, email));
	}

	public void deleteAddressBookEntry(String name) throws EmailException {
		AddressBookDAO dao = AddressBookDAO.instance();
		dao.delete(name);
	}

	public List<AddressBookEntry> listAddressBook() throws EmailException {
		return AddressBookDAO.instance().findAll();
	}

}
