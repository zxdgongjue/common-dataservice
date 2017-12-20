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

import org.acumos.cds.domain.MLPRole;

/**
 * Defines methods to manipulate role and role-function information.
 *
 * A role function cannot exist without a role. Because of this close linkage,
 * both types are handled in this service.
 */
public interface RoleSearchService {

	/**
	 * Gets all instances matching all query parameters; i.e., the conditions are
	 * AND-ed together.
	 * 
	 * @param queryParameters
	 *            field-name, field-value pairs; ignored if null or empty.
	 * @param isOr
	 *            If true, the query is a disjunction ("or"); otherwise the query is
	 *            a conjunction ("and").
	 * @return List of instances, which may be empty.
	 */
	List<MLPRole> getRoles(Map<String, String> queryParameters, boolean isOr);

}