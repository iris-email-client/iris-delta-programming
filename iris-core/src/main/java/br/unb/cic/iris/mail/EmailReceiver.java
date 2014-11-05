package br.unb.cic.iris.mail;

import static br.unb.cic.iris.i18n.Message.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SearchTerm;

import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.model.EmailMessage;
import br.unb.cic.iris.core.model.IrisFolder;

public class EmailReceiver implements StoreListener, FolderListener {
	private Store store;
	private EmailSession session;
	private EmailProvider provider;

	public EmailReceiver(EmailProvider provider, String encoding) {
		this.provider = provider;
		session = new EmailSession(provider, encoding);
	}

	public List<IrisFolder> listFolders() throws EmailException {
		List<IrisFolder> folders = new ArrayList<>();
		try {
			Store store = getStore();
			Folder defaultFolder = store.getDefaultFolder();
			Folder[] externalFolders = defaultFolder.list();
			for (Folder f : externalFolders) {
				folders.add(new IrisFolder(f.getName()));
			}
		} catch (MessagingException e) {
			throw new EmailException(message("error.list.folder"), e);
		}
		return folders;
	}

	public List<EmailMessage> getMessages(String folderName,
			SearchTerm searchTerm) throws EmailException {
		List<EmailMessage> messages = new ArrayList<>();

		Folder folder = openFolder(folderName);
		try {
			Message messagesRetrieved[] = null;
			if (searchTerm == null) {
				// list all
				messagesRetrieved = folder.getMessages();
			} else {
				// search mail
				messagesRetrieved = folder.search(searchTerm);
			}

			messages = convertToIrisMessage(messagesRetrieved);

			// TODO deixa o store aberto mesmo?
			// store.close();
		} catch (MessagingException e) {
			//TODO fazer tratamento de excecoes em tudo
			throw new EmailException(e.getMessage(), e);
		} 

		return messages;
	}
	
	public List<EmailMessage> getMessages(String folderName, int begin, int end) throws EmailException {
		List<EmailMessage> messages = new ArrayList<>();
		Folder folder = openFolder(folderName);
		try {
			Message messagesRetrieved[] = folder.getMessages(begin,end);
			messages = convertToIrisMessage(messagesRetrieved);
		} catch (MessagingException e) {
			throw new EmailException(e.getMessage(), e);
		} 
		return messages;
	}
	
	public List<EmailMessage> getMessages(String folderName, int seqnum) throws EmailException{
		List<EmailMessage> messages = new ArrayList<>();
		Folder folder = openFolder(folderName);
		try {
			List<Message> messagesList = new ArrayList<>();
			int messageCount = folder.getMessageCount();
			for(int i = seqnum; i <= messageCount; i++) {
	            Message message = folder.getMessage(i);
	            messagesList.add(message);
	        }

			Message[] messagesRetrieved = toArray(messagesList);
			messages = convertToIrisMessage(messagesRetrieved);
		} catch (MessagingException e) {
			throw new EmailException(e.getMessage(), e);
		} 
		return messages;
	}

	private Message[] toArray(List<Message> messagesList){
		return messagesList.toArray(new Message[messagesList.size()]);
	}
	
	private List<EmailMessage> convertToIrisMessage(Message[] messagesRetrieved) throws EmailException {
		List<EmailMessage> messages = new ArrayList<>();
		int cont = 0;
		int total = messagesRetrieved.length;
		for (Message m : messagesRetrieved) {
			try {
				messages.add(convertToIrisMessage(m));
				
				// TODO arrumar progresso
				if (total != 0) {
					for (int i = 0; i < 15; i++) {
						System.out.print('\b');
					}
					cont++;
					System.out.print((100 * cont / total) + "% completed");
				}
			} catch (IOException e) {
				throw new EmailException(e.getMessage(), e);
			} catch (MessagingException e) {
				throw new EmailException(e.getMessage(), e);
			}
		}
		System.out.println();

		return messages;
	}

	private Folder openFolder(String folderName) throws EmailException{
		return openFolder(folderName, Folder.READ_ONLY);
	}
	private Folder openFolder(String folderName, int openType) throws EmailException{
		try {
			Folder folder = getStore().getFolder(folderName);
			folder.open(openType);
			
			return folder;
		} catch (MessagingException e) {
			throw new EmailException(e.getMessage(), e);
		} catch (EmailException e) {
			throw new EmailException(e.getMessage(), e);
		}
	}
	private EmailMessage convertToIrisMessage(Message message) throws IOException,
			MessagingException {
		MimeMessage m = (MimeMessage) message;
		// System.out.println("Converting to iris: "+m.getSubject());
		EmailMessage msg = new EmailMessage();
		//msg.setUid(m.getMessageID());//TODO tratar null
		msg.setBcc(convertAddressToString(m.getRecipients(RecipientType.BCC)));
		msg.setCc(convertAddressToString(m.getRecipients(RecipientType.CC)));
		msg.setTo(convertAddressToString(m.getRecipients(RecipientType.TO)));
		msg.setFrom(convertAddressToString(m.getFrom()));
		msg.setMessage(m.getContent().toString());
		msg.setSubject(m.getSubject());
		msg.setDate(m.getReceivedDate());
		return msg;
	}

	private String convertAddressToString(Address[] recipients) {
		StringBuilder sb = new StringBuilder("");
		if (recipients != null) {
			for (Address a : recipients) {
				sb.append(a.toString() + ", ");
			}
		}
		return sb.toString();
	}

	private Store createStoreAndConnect() throws MessagingException {
		System.out.println("Creating store ...");
		Store store = session.getSession()
				.getStore(provider.getStoreProtocol());
		store.addStoreListener(this);
		store.addConnectionListener(session);

		session.connect(store, provider.getStoreHost(), provider.getStorePort());

		return store;
	}
	
	public Store getStore() throws EmailException {
		if (store == null) {
			try {
				store = createStoreAndConnect();
			} catch (MessagingException e) {
				throw new EmailException(e.getMessage(), e);
			}
		}
		return store;
	}

	public Store renew() throws EmailException {
		if (store != null) {
			try {
				store.close();
			} catch (MessagingException e) {
				throw new EmailException(e.getMessage(), e);
			}
			store = null;
		}
		return getStore();
	}

	@Override
	public void notification(StoreEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Notification: " + e.getMessage());
	}

	@Override
	public void folderCreated(FolderEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void folderDeleted(FolderEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void folderRenamed(FolderEvent e) {
		// TODO Auto-generated method stub

	}
}
