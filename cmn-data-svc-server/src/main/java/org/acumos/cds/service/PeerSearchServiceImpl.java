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

package org.acumos.cds.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.acumos.cds.domain.MLPPeer;
import org.acumos.cds.util.EELFLoggerDelegate;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Hibernate-assisted methods to search peer information.
 */
@Service("peerSearchService")
@Transactional
public class PeerSearchServiceImpl extends AbstractSearchServiceImpl implements PeerSearchService {

	private final EELFLoggerDelegate logger = EELFLoggerDelegate.getLogger(PeerSearchServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MLPPeer> getPeers(Map<String, ? extends Object> queryParameters, boolean isOr) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MLPPeer.class);
		super.buildCriteria(criteria, queryParameters, isOr);
		Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		List<MLPPeer> items = criteria.list();
		tx.commit();
		logger.debug(EELFLoggerDelegate.debugLogger, "getPeers: result size={}", items.size());
		return items;
	}
}