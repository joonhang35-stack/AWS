package com.bcs.zsg.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcs.zsg.component.integration.config.IntegrationConfigManager;
import com.bcs.zsg.core.startup.StartupItem;

public class AppStartupItem implements StartupItem {
private static final Logger logger = LoggerFactory.getLogger(AppStartupItem.class);
	
	public void execute() {
		try {
			IntegrationConfigManager.getInstance().loadConfig();
			
			new Thread(new AppStartupThread()).start();
			
		} catch (Exception e) {
			logger.error("Failed to load integration configuration.", e);
		}
	}
}
