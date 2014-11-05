package br.unb.cic.iris.mail.provider;

import java.util.Properties;

import br.unb.cic.iris.mail.EmailProvider;

public abstract class AbstractEmailProvider implements EmailProvider {
	private String name;
	private String description;
	private String transportProtocol;// smtps
	private String transportHost;// smtp.gmail.com
	private int transportPort; // 587
	private String storeProtocol;// imaps
	private String storeHost;// imap.gmail.com
	private int storePort;// 993
	private String username;
	private String password;
	private boolean authenticationEnabled = true;

	private Properties properties = new Properties();

	@Override
	public Properties getProperties() {
		return properties;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public String getTransportProtocol() {
		return transportProtocol;
	}

	protected void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	public String getStoreProtocol() {
		return storeProtocol;
	}

	protected void setStoreProtocol(String storeProtocol) {
		this.storeProtocol = storeProtocol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}

	protected void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

	public String getTransportHost() {
		return transportHost;
	}

	protected void setTransportHost(String transportHost) {
		this.transportHost = transportHost;
	}

	public int getTransportPort() {
		return transportPort;
	}

	protected void setTransportPort(int transportPort) {
		this.transportPort = transportPort;
	}

	public String getStoreHost() {
		return storeHost;
	}

	protected void setStoreHost(String storeHost) {
		this.storeHost = storeHost;
	}

	public int getStorePort() {
		return storePort;
	}

	protected void setStorePort(int storePort) {
		this.storePort = storePort;
	}

	@Override
	public EmailProvider clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
