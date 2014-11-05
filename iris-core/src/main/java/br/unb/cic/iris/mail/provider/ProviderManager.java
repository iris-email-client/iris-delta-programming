package br.unb.cic.iris.mail.provider;

import java.util.List;

import br.unb.cic.iris.core.BaseManager;
import br.unb.cic.iris.mail.EmailProvider;

public class ProviderManager {
	private static ProviderManager instance = new ProviderManager();

	private BaseManager<EmailProvider> manager;

	private ProviderManager() {
		manager = new BaseManager<>();
		addProvider(new GmailProvider());
	}

	public static ProviderManager instance() {
		return instance;
	}

	@SuppressWarnings("boxing")
	public void addProvider(EmailProvider provider) {
		manager.add(provider.getName().trim(), provider);
	}

	@SuppressWarnings("boxing")
	public EmailProvider getProvider(String name) {
		return manager.get(name);
	}

	public List<EmailProvider> getProviders() {
		return manager.getAll();
	}

}
