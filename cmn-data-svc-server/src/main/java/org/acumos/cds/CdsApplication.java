/*-
 * ===============LICENSE_START=======================================================
 * Acumos
 * ===================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
 * ===================================================================================
 * This Acumos software file is distributed by AT&T and Tech Mahindra
 * under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * This file is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===============LICENSE_END=========================================================
 */

package org.acumos.cds;

import java.io.IOException;

import org.acumos.cds.util.EELFLoggerDelegate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Launcher for the common data service Spring-Boot app.
 */
@SpringBootApplication
public class CdsApplication implements ApplicationContextAware {

	private static final EELFLoggerDelegate logger = EELFLoggerDelegate.getLogger(CdsApplication.class);

	public static final String CONFIG_ENV_VAR_NAME = "SPRING_APPLICATION_JSON";

	/**
	 * Emits a message about the environment configuration. Runs only when started
	 * on the command line via java -jar ..; does not run when started by test use
	 * of Spring Runner.
	 * 
	 * This method uses the default logger (i.e., does not select the application or
	 * debug logger) at level info to ensure its messages appear at startup on
	 * stdout.
	 * 
	 * @param args
	 *            Command line
	 * @throws IOException
	 *             If JSON is present but cannot be parsed
	 */
	public static void main(String[] args) throws IOException {
		final String springApplicationJson = System.getenv(CONFIG_ENV_VAR_NAME);
		if (springApplicationJson != null && springApplicationJson.contains("{")) {
			final ObjectMapper mapper = new ObjectMapper();
			// ensure it's valid; Spring silently ignores if not parseable
			mapper.readTree(springApplicationJson);
			logger.info("main: successfully parsed configuration from environment {}", CONFIG_ENV_VAR_NAME);
		} else {
			logger.warn("main: no configuration found in environment {}", CONFIG_ENV_VAR_NAME);
		}
		ConfigurableApplicationContext cxt = SpringApplication.run(CdsApplication.class, args);
		logger.info("main: context is {}", cxt);
		// Closing the context stops the application, so ignore
		// the Sonar advice that the context must be closed here!
	}

	@Override
	public void setApplicationContext(ApplicationContext context) {
		final String activeProfile = "src";
		logger.info("setApplicationContext: setting profile {}", activeProfile);
		((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles(activeProfile);
	}

}
