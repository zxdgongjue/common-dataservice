-- ===============LICENSE_START=======================================================
-- Acumos Apache-2.0
-- ===================================================================================
-- Copyright (C) 2017-2018 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
-- ===================================================================================
-- This Acumos software file is distributed by AT&T and Tech Mahindra
-- under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--  
--      http://www.apache.org/licenses/LICENSE-2.0
--  
-- This file is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- ===============LICENSE_END=========================================================

-- Script to downgrade database used by the Common Data Service
-- FROM version 1.12.x TO version 1.11.x.
-- No database is created or specified to allow flexible deployment!

-- Undo: 5 add rows
DELETE FROM C_TOOLKIT_TYPE WHERE TYPE_CD = 'TC';
DELETE FROM C_TOOLKIT_TYPE WHERE TYPE_CD = 'BR';
-- Undo: 4 add the constraint
ALTER TABLE C_SOLUTION DROP FOREIGN KEY C_SOLUTION_C_PEER;
-- Undo: 3 add the column
ALTER TABLE C_SOLUTION DROP COLUMN SOURCE_ID;
-- Undo: 2 add the constraint
ALTER TABLE C_PEER_SUB DROP FOREIGN KEY C_PEER_SUB_C_USER;
-- Undo: 1 add the column
ALTER TABLE C_PEER_SUB DROP COLUMN OWNER_ID;
