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

package org.acumos.cds.client.test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.acumos.cds.client.CommonDataServiceRestClientImpl;
import org.acumos.cds.client.ICommonDataServiceRestClient;
import org.acumos.cds.domain.MLPArtifact;
import org.acumos.cds.domain.MLPSolution;
import org.acumos.cds.domain.MLPSolutionRevision;
import org.acumos.cds.domain.MLPUser;
import org.acumos.cds.transport.RestPageRequest;
import org.acumos.cds.transport.RestPageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Demonstrates use of the CDS client.
 */
public class BasicSequenceDemo {

	private static Logger logger = LoggerFactory.getLogger(BasicSequenceDemo.class);

	private static String hostname = "localhost";
	private static final String contextPath = "/ccds";
	private static final int port = 8080;
	private static final String userName = "cds_web_user";
	private static final String password = "cds_web_pass";

	public static void main(String[] args) throws Exception {

		URL url = new URL("http", hostname, port, contextPath);
		logger.info("createClient: URL is {}", url);
		ICommonDataServiceRestClient client = CommonDataServiceRestClientImpl.getInstance(url.toString(), userName,
				password);

		try {
			MLPUser cu = new MLPUser("user_login1", "login1user@abc.com", true);
			cu.setLoginHash("user_pass");
			cu.setFirstName("First Name");
			cu.setLastName("Last Name");
			cu.setEmail(cu.getLoginName() + "@nowhere.com");
			cu = client.createUser(cu);
			logger.info("Created user {}", cu);

			MLPSolution cs = new MLPSolution("solution name", cu.getUserId(), true);
			cs.setProvider("Big Data Org");
			cs.setModelTypeCode("CL");
			cs.setToolkitTypeCode("CP");
			cs = client.createSolution(cs);
			logger.info("Created solution {}", cs);

			MLPSolutionRevision cr = new MLPSolutionRevision(cs.getSolutionId(), "1.0R", cu.getUserId(), "PB", "IP");
			cr.setDescription("Some description");
			cr = client.createSolutionRevision(cr);
			logger.info("Created solution revision {}", cr);

			MLPArtifact ca = new MLPArtifact("1.0A", "DI", "artifact name", "http://nexus/artifact", cu.getUserId(), 1);
			ca = client.createArtifact(ca);
			logger.info("Created artifact {}", ca);

			logger.info("Adding artifact to revision");
			client.addSolutionRevisionArtifact(cs.getSolutionId(), cr.getRevisionId(), ca.getArtifactId());

			logger.info("Searching for active solutions");
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("active", Boolean.TRUE);
			RestPageResponse<MLPSolution> activeSolutions = client.searchSolutions(queryParams, false,
					new RestPageRequest(0, 10));
			logger.info("Active solution count: " + activeSolutions.getTotalElements());

			logger.info("Deleting objects");
			client.dropSolutionRevisionArtifact(cs.getSolutionId(), cr.getRevisionId(), ca.getArtifactId());
			client.deleteArtifact(ca.getArtifactId());
			client.deleteSolutionRevision(cs.getSolutionId(), cr.getRevisionId());
			client.deleteSolution(cs.getSolutionId());
			client.deleteUser(cu.getUserId());

		} catch (HttpStatusCodeException ex) {
			logger.error("basicSequenceDemo failed, server reports: {}", ex.getResponseBodyAsString());
			throw ex;
		}
	}

}
