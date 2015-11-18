package org.wso2.carbon.device.mgt.common.api.startup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.core.ServerStartupObserver;
import org.wso2.carbon.device.mgt.common.api.internal.DeviceManagementServiceComponent;
import org.wso2.carbon.utils.CarbonUtils;
import org.wso2.carbon.utils.ConfigurationContextService;
import org.wso2.carbon.utils.NetworkUtils;

public class StartupUrlPrinter implements ServerStartupObserver {
	private static final Log log = LogFactory.getLog(StartupUrlPrinter.class);
	@Override
	public void completingServerStartup() {

	}

	@Override
	public void completedServerStartup() {
		printUrl();


	}
	private void printUrl() {
		// Hostname
		String hostName = "localhost";
		try {
			hostName = NetworkUtils.getMgtHostName();
		} catch (Exception ignored) {
		}
		// HTTPS port
		String mgtConsoleTransport = CarbonUtils.getManagementTransport();
		ConfigurationContextService configContextService = DeviceManagementServiceComponent.configurationContextService;

		int httpsPort = CarbonUtils.getTransportPort(configContextService, mgtConsoleTransport);

		log.info("CDM - Device Common API Service: https://" + hostName + ":" + httpsPort + "/common");
	}

}
