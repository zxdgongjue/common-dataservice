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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acumos.cds.client.CommonDataServiceRestClientMockImpl;
import org.acumos.cds.domain.MLPArtifact;
import org.acumos.cds.domain.MLPComment;
import org.acumos.cds.domain.MLPNotification;
import org.acumos.cds.domain.MLPPasswordChangeRequest;
import org.acumos.cds.domain.MLPPeer;
import org.acumos.cds.domain.MLPPeerSubscription;
import org.acumos.cds.domain.MLPRole;
import org.acumos.cds.domain.MLPRoleFunction;
import org.acumos.cds.domain.MLPSiteConfig;
import org.acumos.cds.domain.MLPSolution;
import org.acumos.cds.domain.MLPSolutionDeployment;
import org.acumos.cds.domain.MLPSolutionDownload;
import org.acumos.cds.domain.MLPSolutionFavorite;
import org.acumos.cds.domain.MLPSolutionRating;
import org.acumos.cds.domain.MLPSolutionRevision;
import org.acumos.cds.domain.MLPSolutionValidation;
import org.acumos.cds.domain.MLPSolutionWeb;
import org.acumos.cds.domain.MLPTag;
import org.acumos.cds.domain.MLPThread;
import org.acumos.cds.domain.MLPUser;
import org.acumos.cds.domain.MLPUserLoginProvider;
import org.acumos.cds.domain.MLPUserNotification;
import org.acumos.cds.domain.MLPValidationSequence;
import org.acumos.cds.transport.RestPageRequest;
import org.acumos.cds.transport.RestPageResponse;
import org.acumos.cds.transport.SuccessTransport;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;


/**
 * Cover the mock methods.
 */
public class MockClientTest {

		static class TrivialRestClientMockImplSubclass extends CommonDataServiceRestClientMockImpl {
		public TrivialRestClientMockImplSubclass(String webapiUrl, String user, String pass) {
			super(webapiUrl, user, pass);
			super.getRestTemplate();
		}
	}
		
	@Test
	public void coverMockMethods() {
		
		new TrivialRestClientMockImplSubclass("usl", "usr", "pass");
		CommonDataServiceRestClientMockImpl.getInstance("url", null);
		CommonDataServiceRestClientMockImpl.getInstance("url", "user", "pass");
		
		CommonDataServiceRestClientMockImpl client = new CommonDataServiceRestClientMockImpl();		
		client =  new CommonDataServiceRestClientMockImpl("url", "user", "pass");
		client =  new CommonDataServiceRestClientMockImpl("url", new RestTemplate());
				
		RestPageRequest pageRequest = new RestPageRequest(0,1);
		Map<String,Object> queryParameters = new HashMap<>();
		boolean isOr = false;
		List<String> stringList = new ArrayList<>();
		
		SuccessTransport success = new SuccessTransport();
		client.setHealth(success);
		Assert.assertTrue(success == client.getHealth());
		client.setVersion(success);
		Assert.assertTrue(success == client.getVersion());
		
		Assert.assertFalse(client.getAccessTypes().isEmpty());
		Assert.assertFalse(client.getArtifactTypes().isEmpty());
		Assert.assertFalse(client.getLoginProviders().isEmpty());
		Assert.assertFalse(client.getModelTypes().isEmpty());
		Assert.assertFalse(client.getToolkitTypes().isEmpty());
		Assert.assertFalse(client.getValidationStatuses().isEmpty());
		Assert.assertFalse(client.getValidationTypes().isEmpty());
		Assert.assertFalse(client.getDeploymentStatuses().isEmpty());
		
		long count = 3;
		client.setSolutionCount(count);
		Assert.assertTrue(count == client.getSolutionCount());

		RestPageResponse<MLPSolution> solutions = new RestPageResponse<>();
		client.setSolutions(solutions);
		Assert.assertTrue(solutions == client.getSolutions(pageRequest));			
		client.setSolutionsBySearchTerm(solutions);
		Assert.assertTrue(solutions == client.findSolutionsBySearchTerm("string", pageRequest));
		client.setSolutionsByTag(solutions);
		Assert.assertTrue(solutions == client.findSolutionsByTag("string", pageRequest));
		
		MLPSolution solution = new MLPSolution();
		client.setSolutionById(solution);
		Assert.assertTrue(solution == client.getSolution("id"));
		client.setSolution(solution);
		Assert.assertTrue(solution == client.createSolution(solution));
		client.updateSolution(solution);
		client.incrementSolutionViewCount("id");
		client.deleteSolution("id");
		
		List<MLPSolutionRevision> solRevList = new ArrayList<>();
		client.setSolutionRevisionsById(solRevList);
		Assert.assertTrue(solRevList == client.getSolutionRevisions("id"));
		client.setSolutionRevisionsByIdList(solRevList);
		Assert.assertTrue(solRevList == client.getSolutionRevisions(new String[] {"id"}));
		client.setSolutionRevisionsForArtifact(solRevList);
		Assert.assertTrue(solRevList == client.getSolutionRevisionsForArtifact("id"));
		
		MLPSolutionRevision solRev = new MLPSolutionRevision();
		client.setSolutionRevisionById(solRev);
		Assert.assertTrue(solRev == client.getSolutionRevision("sid", "rid"));
		client.setSolutionRevision(solRev);
		Assert.assertTrue(solRev == client.createSolutionRevision(solRev));
		client.updateSolutionRevision(solRev);
		client.deleteSolutionRevision("id", "id");
		
		List<MLPArtifact> artList = new ArrayList<>();
		client.setSolutionRevisionArtifacts(artList);
		Assert.assertTrue(artList == client.getSolutionRevisionArtifacts("id", "id"));
		client.addSolutionRevisionArtifact("id", "id", "id");
		client.dropSolutionRevisionArtifact("id", "id", "id");
	
		RestPageResponse<MLPTag> tags = new RestPageResponse<>();
		client.setTags(tags);
		Assert.assertTrue(tags == client.getTags(pageRequest));
		
		MLPTag tag = new MLPTag();
		client.setTag(tag);
		Assert.assertTrue(tag == client.createTag(tag));
		client.deleteTag(tag);

		List<MLPTag> tagList = new ArrayList<>();
		client.setSolutionTags(tagList);
		Assert.assertTrue(tagList == client.getSolutionTags("id"));
		client.addSolutionTag("id", "tag");
		client.dropSolutionTag("id", "tag");
		
		client.setArtifactCount(count);
		Assert.assertTrue(count == client.getArtifactCount());
		
		RestPageResponse<MLPArtifact> artifacts = new RestPageResponse<>();
		client.setArtifacts(artifacts);
		Assert.assertTrue(artifacts == client.getArtifacts(pageRequest));
		client.setArtifactsBySearchTerm(artifacts);
		Assert.assertTrue(artifacts == client.findArtifactsBySearchTerm("search", pageRequest));

		client.setSearchArtifacts(artList);
		Assert.assertTrue(artList == client.searchArtifacts(queryParameters, isOr));
		
		MLPArtifact artifact = new MLPArtifact();
		client.setArtifactById(artifact);
		Assert.assertTrue(artifact == client.getArtifact("id"));
		client.setArtifact(artifact);
		Assert.assertTrue(artifact == client.createArtifact(artifact));
		client.updateArtifact(artifact);
		client.deleteArtifact("id");
		
		client.setUserCount(count);
		Assert.assertTrue(count == client.getUserCount());
		
		RestPageResponse<MLPUser> users = new RestPageResponse<>();
		client.setUsers(users);
		Assert.assertTrue(users == client.getUsers(pageRequest));
		client.setUsersBySearchTerm(users);
		Assert.assertTrue(users == client.findUsersBySearchTerm("term", pageRequest));
		
		List<MLPUser> userList = new ArrayList<>();
		client.setSearchUsers(userList);
		Assert.assertTrue(userList == client.searchUsers(queryParameters, isOr));
		
		MLPUser user = new MLPUser();
		client.setLoginUser(user);
		Assert.assertTrue(user == client.loginUser("name", "pass"));
		
		client.setUserById(user);
		Assert.assertTrue(user == client.getUser("id"));
		client.setUser(user);
		Assert.assertTrue(user == client.createUser(user));
		client.updateUser(user);
		client.deleteUser("id");
		
		List<MLPRole> roleList = new ArrayList<>();
		client.setUserRoles(roleList);
		Assert.assertTrue(roleList == client.getUserRoles("id"));
		client.addUserRole("id", "id");
		client.updateUserRoles("id", stringList);
		client.dropUserRole("user", "role");
		client.addUsersInRole(stringList,  "role");
		client.dropUsersInRole(stringList,  "role");
		
		client.setRoleUsersCount(count);
		Assert.assertTrue(count == client.getRoleUsersCount("role"));
		
		MLPUserLoginProvider userLoginProvider = new MLPUserLoginProvider();
		client.setUserLoginProviderById(userLoginProvider);
		Assert.assertTrue(userLoginProvider == client.getUserLoginProvider("id", "code", "login"));

		List<MLPUserLoginProvider> userLoginProviderList = new ArrayList<>();
		client.setUserLoginProviders(userLoginProviderList);
		Assert.assertTrue(userLoginProviderList == client.getUserLoginProviders("id"));
		
		client.setUserLoginProvider(userLoginProvider);
		Assert.assertTrue(userLoginProvider == client.createUserLoginProvider(userLoginProvider));
		client.updateUserLoginProvider(userLoginProvider);
		client.deleteUserLoginProvider(userLoginProvider);
		
		client.setRoleCount(count);
		Assert.assertTrue(count == client.getRoleCount());
		
		client.setSearchRoles(roleList);
		Assert.assertTrue(roleList == client.searchRoles(queryParameters, isOr));
		
		RestPageResponse<MLPRole> roles = new RestPageResponse<>();
		client.setRoles(roles);
		Assert.assertTrue(roles == client.getRoles(pageRequest));
		
		MLPRole role = new MLPRole();
		client.setRoleById(role);
		Assert.assertTrue(role == client.getRole("id"));
		client.setRole(role);
		Assert.assertTrue(role == client.createRole(role));
		client.updateRole(role);
		client.deleteRole("id");
		
		List<MLPRoleFunction> functions = new ArrayList<>();
		client.setRoleFunctions(functions);
		Assert.assertTrue(functions == client.getRoleFunctions("id"));
		
		MLPRoleFunction roleFunction = new MLPRoleFunction();
		client.setRoleFunctionById(roleFunction);
		Assert.assertTrue(roleFunction == client.getRoleFunction("id", "id"));	
		client.setRoleFunction(roleFunction);
		Assert.assertTrue(roleFunction == client.createRoleFunction(roleFunction));
		client.updateRoleFunction(roleFunction);
		client.deleteRoleFunction("id", "id");
		
		RestPageResponse<MLPPeer> peers = new RestPageResponse<>();
		client.setPeers(peers);
		Assert.assertTrue(peers == client.getPeers(pageRequest));
		
		List<MLPPeer> peerList = new ArrayList<>();
		client.setSearchPeers(peerList);
		Assert.assertTrue(peerList == client.searchPeers(queryParameters, isOr));
		
		MLPPeer peer = new MLPPeer();
		client.setPeerById(peer);
		Assert.assertTrue(peer == client.getPeer("id"));
		client.setPeer(peer);
		Assert.assertTrue(peer == client.createPeer(peer));
		client.updatePeer(peer);;
		client.deletePeer("id");
		
		List<MLPPeerSubscription> peerSubList = new ArrayList<>();
		client.setPeerSubscriptions(peerSubList);
		Assert.assertTrue(peerSubList == client.getPeerSubscriptions("id"));
		
		MLPPeerSubscription peerSub = new MLPPeerSubscription();
		client.setPeerSubscriptionById(peerSub);
		Assert.assertTrue(peerSub == client.getPeerSubscription(0L));
		client.setPeerSubscription(peerSub);
		Assert.assertTrue(peerSub == client.createPeerSubscription(peerSub));
		client.updatePeerSubscription(peerSub);
		client.deletePeerSubscription(0L);
		
		RestPageResponse<MLPSolutionDownload> solutionDownloads = new RestPageResponse<>();
		client.setSolutionDownloads(solutionDownloads);
		Assert.assertTrue(solutionDownloads == client.getSolutionDownloads("id", pageRequest));
		
		MLPSolutionDownload solutionDownload = new MLPSolutionDownload();
		client.setSolutionDownload(solutionDownload);
		Assert.assertTrue(solutionDownload == client.createSolutionDownload(solutionDownload));
		client.deleteSolutionDownload(solutionDownload);
		
		client.setFavoriteSolutions(solutions);
		Assert.assertTrue(solutions == client.getFavoriteSolutions("id", pageRequest));
		
		MLPSolutionFavorite favorite = new MLPSolutionFavorite();
		client.setSolutionFavorite(favorite);
		Assert.assertTrue(favorite == client.createSolutionFavorite(favorite));
		client.deleteSolutionFavorite(favorite);
		
		RestPageResponse<MLPSolutionRating> ratings = new RestPageResponse<>();
		client.setSolutionRatings(ratings);
		Assert.assertTrue(ratings == client.getSolutionRatings("id", pageRequest));
		
		MLPSolutionRating rating = new MLPSolutionRating();
		client.setUserSolutionRating(rating);
		Assert.assertTrue(rating == client.getSolutionRating("id", "id"));
		client.setSolutionRating(rating);
		Assert.assertTrue(rating == client.createSolutionRating(rating));
		client.updateSolutionRating(rating);
		client.deleteSolutionRating(rating);
		
		client.setNotificationCount(count);
		Assert.assertTrue(count == client.getNotificationCount());
		
		RestPageResponse<MLPNotification> notifications = new RestPageResponse<>();
		client.setNotifications(notifications);
		Assert.assertTrue(notifications == client.getNotifications(pageRequest));
		
		MLPNotification notification = new MLPNotification();
		client.setNotification(notification);
		Assert.assertTrue(notification == client.createNotification(notification));
		client.updateNotification(notification);
		client.deleteNotification("id");
		
		RestPageResponse<MLPUserNotification> userNotifications = new RestPageResponse<>();
		client.setUserNotifications(userNotifications);
		Assert.assertTrue(userNotifications == client.getUserNotifications("id", pageRequest));
		client.addUserToNotification("id", "id");
		client.dropUserFromNotification("id", "id");
		client.setUserViewedNotification("id", "id");
		
		MLPSolutionWeb web = new MLPSolutionWeb();
		client.setSolutionWebMetadata(web);
		Assert.assertTrue(web == client.getSolutionWebMetadata("id"));
		
		client.setSolutionAccessUsers(userList);
		Assert.assertTrue(userList == client.getSolutionAccessUsers("id"));
		
		client.setUserAccessSolutions(solutions);
		Assert.assertTrue(solutions == client.getUserAccessSolutions("id", pageRequest));
		client.addSolutionUserAccess("id", "id");
		client.dropSolutionUserAccess("id", "id");
		
		client.updatePassword(user, new MLPPasswordChangeRequest());
		
		List<MLPSolutionValidation> validationList = new ArrayList<>();
		client.setSolutionValidations(validationList);
		Assert.assertTrue(validationList == client.getSolutionValidations("id", "id"));

		MLPSolutionValidation validation = new MLPSolutionValidation();
		client.setSolutionValidation(validation);
		Assert.assertTrue(validation == client.createSolutionValidation(validation));
		client.updateSolutionValidation(validation);
		client.deleteSolutionValidation(validation);
		
		List<MLPValidationSequence> validationSequenceList = new ArrayList<>();
		client.setValidationSequences(validationSequenceList);
		Assert.assertTrue(validationSequenceList == client.getValidationSequences());
		
		MLPValidationSequence valSeq = new MLPValidationSequence();
		client.setValidationSequence(valSeq);
		Assert.assertTrue(valSeq==client.createValidationSequence(valSeq));
		client.deleteValidationSequence(valSeq);
		
		RestPageResponse<MLPSolutionDeployment> deployments = new RestPageResponse<>();
		client.setUserDeployments(deployments);
		Assert.assertTrue(deployments == client.getUserDeployments("id", pageRequest));
		client.setSolutionDeployments(deployments);
		Assert.assertTrue(deployments == client.getSolutionDeployments("id", "id", pageRequest));
		client.setUserSolutionDeployments(deployments);
		Assert.assertTrue(deployments == client.getUserSolutionDeployments("id", "id", "id", pageRequest));

		MLPSolutionDeployment deployment = new MLPSolutionDeployment();
		client.setSolutionDeployment(deployment);
		Assert.assertTrue(deployment == client.createSolutionDeployment(deployment));
		client.updateSolutionDeployment(deployment);
		client.deleteSolutionDeployment(deployment);
		
		MLPSiteConfig siteConfig = new MLPSiteConfig();
		client.setSiteConfigByKey(siteConfig);
		Assert.assertTrue(siteConfig == client.getSiteConfig("key"));
		client.setSiteConfig(siteConfig);
		Assert.assertTrue(siteConfig == client.createSiteConfig(siteConfig));
		client.updateSiteConfig(siteConfig);
		client.deleteSiteConfig("key");
		
		client.setThreadCount(count);
		Assert.assertTrue(count == client.getThreadCount());
		
		RestPageResponse<MLPThread> threads = new RestPageResponse<>();
		client.setThreads(threads);
		Assert.assertTrue(threads == client.getThreads(pageRequest));
		
		MLPThread thread = new MLPThread();
		client.setThreadById(thread);
		Assert.assertTrue(thread == client.getThread("id"));
		client.setThread(thread);
		Assert.assertTrue(thread == client.createThread(thread));
		client.updateThread(thread);
		client.deleteThread("id");
		
		client.setThreadCommentCount(count);
		Assert.assertTrue(count == client.getThreadCommentCount("id"));
		
		RestPageResponse<MLPComment> comments = new RestPageResponse<>();
		client.setThreadComments(comments);
		Assert.assertTrue(comments == client.getThreadComments("id", pageRequest));
		
		MLPComment comment = new MLPComment();
		client.setCommentById(comment);
		Assert.assertTrue(comment == client.getComment("id", "id"));
		client.setComment(comment);
		Assert.assertTrue(comment == client.createComment(comment));
		client.updateComment(comment);
		client.deleteComment("id", "id");
		
		client.setPortalSolutions(solutions);
		Assert.assertTrue(solutions == client.findPortalSolutions(null, null, true, null, null, null, null, null, pageRequest));
	
		List<MLPSolution> solutionList = new ArrayList<>();
		client.setSearchSolutions(solutionList);
		Assert.assertTrue(solutionList == client.searchSolutions(queryParameters, isOr));
	
		client.setSolutionRevisionThreads(threads);
		Assert.assertTrue(threads == client.getSolutionRevisionThreads("id", "id", pageRequest));
		
		client.setSolutionRevisionComments(comments);
		Assert.assertTrue(comments == client.getSolutionRevisionComments("id", "id", pageRequest));
		
	}
}