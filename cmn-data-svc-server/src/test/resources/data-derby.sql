---
-- ===============LICENSE_START=======================================================
-- Acumos
-- ===================================================================================
-- Copyright (C) 2017 AT&T Intellectual Property & Tech Mahindra. All rights reserved.
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
---

-- Spring-Boot runs this script DURING TEST after creating the schema from annotations.
-- Need to keep in synch with the main dml/ddl script!

-- This file is src/test/resources/data-derby.sql, where "derby" is the
-- spring.datasource.platform property. This tries to ensure that data
-- is only auto-loaded during test AND when using a Derby database.

INSERT INTO C_ACCESS_TYPE (TYPE_CD, TYPE_NAME) VALUES ('OR', 'Organization');
INSERT INTO C_ACCESS_TYPE (TYPE_CD, TYPE_NAME) VALUES ('PB', 'Public');
INSERT INTO C_ACCESS_TYPE (TYPE_CD, TYPE_NAME) VALUES ('PR', 'Private');

INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('BP', 'BLUEPRINT FILE' );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('CD', 'CDUMP FILE'     );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('DI', 'DOCKER IMAGE'   );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('DS', 'DATA SOURCE'    );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MD', 'METADATA'       );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MH', 'MODEL-H2O'      );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MI', 'MODEL IMAGE'    );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MR', 'MODEL-RCLOUD'   );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MS', 'MODEL-SCIKIT'   );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('MT', 'MODEL-TENSORFLOW');
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TE', 'TOSCA TEMPLATE' );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TG', 'TOSCA Generator Input File');
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TS', 'TOSCA SCHEMA'   );
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TT', 'TOSCA TRANSLATE');
INSERT INTO C_ARTIFACT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('PJ', 'PROTOBUF FILE');

INSERT INTO C_LOGIN_PROVIDER (PROVIDER_CD, PROVIDER_NAME) VALUES ('FB', 'Facebook');
INSERT INTO C_LOGIN_PROVIDER (PROVIDER_CD, PROVIDER_NAME) VALUES ('GH', 'GitHub');
INSERT INTO C_LOGIN_PROVIDER (PROVIDER_CD, PROVIDER_NAME) VALUES ('GP', 'Google Plus');
INSERT INTO C_LOGIN_PROVIDER (PROVIDER_CD, PROVIDER_NAME) VALUES ('LI', 'LinkedIn');

INSERT INTO C_MODEL_TYPE (TYPE_CD, TYPE_NAME) VALUES ('CL', 'Classification');
INSERT INTO C_MODEL_TYPE (TYPE_CD, TYPE_NAME) VALUES ('DS', 'Data Sources');
INSERT INTO C_MODEL_TYPE (TYPE_CD, TYPE_NAME) VALUES ('DT', 'Data Transformer');
INSERT INTO C_MODEL_TYPE (TYPE_CD, TYPE_NAME) VALUES ('PR', 'Prediction');
INSERT INTO C_MODEL_TYPE (TYPE_CD, TYPE_NAME) VALUES ('RG', 'Regression');

INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('CP', 'Composite Solution');
INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('DS', 'Design Studio');
INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('H2', 'H2O');
INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('RC', 'RCloud');
INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('SK', 'Scikit-Learn');
INSERT INTO C_TOOLKIT_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TF', 'TensorFlow');

INSERT INTO C_VALIDATION_STATUS (STATUS_CD, STATUS_NAME) VALUES ('FA', 'Failed');
INSERT INTO C_VALIDATION_STATUS (STATUS_CD, STATUS_NAME) VALUES ('IP', 'In Progress');
INSERT INTO C_VALIDATION_STATUS (STATUS_CD, STATUS_NAME) VALUES ('NV', 'Not Validated');
INSERT INTO C_VALIDATION_STATUS (STATUS_CD, STATUS_NAME) VALUES ('PS', 'Passed');
INSERT INTO C_VALIDATION_STATUS (STATUS_CD, STATUS_NAME) VALUES ('SB', 'Submitted');

INSERT INTO C_VALIDATION_TYPE (TYPE_CD, TYPE_NAME) VALUES ('SS', 'Security Scan');
INSERT INTO C_VALIDATION_TYPE (TYPE_CD, TYPE_NAME) VALUES ('LC', 'License Check');
INSERT INTO C_VALIDATION_TYPE (TYPE_CD, TYPE_NAME) VALUES ('OQ', 'OSS Quantification');
INSERT INTO C_VALIDATION_TYPE (TYPE_CD, TYPE_NAME) VALUES ('TA', 'Text Analysis');

INSERT INTO C_DEPLOYMENT_STATUS (STATUS_CD, STATUS_NAME) VALUES ('DP', 'Deployed');
INSERT INTO C_DEPLOYMENT_STATUS (STATUS_CD, STATUS_NAME) VALUES ('FA', 'Failed');
INSERT INTO C_DEPLOYMENT_STATUS (STATUS_CD, STATUS_NAME) VALUES ('IP', 'In Progress');
INSERT INTO C_DEPLOYMENT_STATUS (STATUS_CD, STATUS_NAME) VALUES ('ST', 'Started');