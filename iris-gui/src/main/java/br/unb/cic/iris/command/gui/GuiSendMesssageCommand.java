package br.unb.cic.iris.command.gui;


import br.unb.cic.iris.command.SendMessageCommand;
import br.unb.cic.iris.core.exception.EmailException;
import br.unb.cic.iris.core.model.EmailMessage;
import br.unb.cic.iris.gui.SendPanel;

public class GuiSendMesssageCommand extends SendMessageCommand {
	private SendPanel panel;

	public GuiSendMesssageCommand(){
		//TODO
		this(null);
	}
	
	public GuiSendMesssageCommand(SendPanel panel) {
		this.panel = panel;
	}

	@Override
	public void explain() {
		// TODO Auto-generated method stub
	}

	@Override
	protected EmailMessage createMessage() {
		String to, subject, content;
		
		to = panel.getTo();
		subject = panel.getSubject();
		content = panel.getContent();

		return new EmailMessage(to, subject, content);
	}

	@Override
	public String getCommandName() {
		return "SendMessageGUI";
	}

	public void setPanel(SendPanel panel) {
		this.panel = panel;
	}

	@Override
	protected void handleExecute() throws EmailException {
		// TODO Auto-generated method stub
		
	}

}
