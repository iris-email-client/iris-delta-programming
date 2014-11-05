package br.unb.cic.iris.mail.provider;

import java.util.Properties;

import br.unb.cic.iris.core.Configuration;
import br.unb.cic.iris.mail.EmailProvider;

public class DefaultProvider extends AbstractEmailProvider {
	private Properties properties = new Properties();

	public DefaultProvider(Properties generalProperties) {
		parseConfiguration(generalProperties);
		properties.putAll(generalProperties);
	}

	private void parseConfiguration(Properties props) {
		System.out.println("Parsing configuration ...");

		// TODO validar config ....

		setDescription(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_DESCRIPTION));
		setName(retrieveAndRemoveProperty(props, Configuration.PROVIDER_NAME));

		setUsername(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_USER));
		setPassword(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_PASSWORD));

		setStoreProtocol(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_STORE_PROTOCOL));
		setStoreHost(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_STORE_HOST));
		setStorePort(Integer.parseInt(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_STORE_PORT)));

		String auth = retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_AUTH);
		setAuthenticationEnabled("true".equalsIgnoreCase(auth) ? true : false);

		setTransportProtocol(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_TRANSPORT_PROTOCOL));
		setTransportHost(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_TRANSPORT_HOST));
		setTransportPort(Integer.parseInt(retrieveAndRemoveProperty(props,
				Configuration.PROVIDER_TRANSPORT_PORT)));

		// System.out.println("Properties after default provider configuration: ");
		// properties.forEach((k, v) ->
		// System.out.printf("   --> %s=%s%n",k,v));
	}

	// removes custom iris properties, left only java mail props ...
	private static String retrieveAndRemoveProperty(Properties props, String key) {
		String value = props.getProperty(key).trim();
		props.remove(key);
		return value;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public EmailProvider clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
