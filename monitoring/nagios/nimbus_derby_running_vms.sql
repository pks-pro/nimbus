-- * Copyright 2009 University of Victoria
-- *
-- * Licensed under the Apache License, Version 2.0 (the "License");
-- * you may not use this file except in compliance with the License.
-- * You may obtain a copy of the License at
-- *
-- * http://www.apache.org/licenses/LICENSE-2.0
-- *
-- * Unless required by applicable law or agreed to in writing, software
-- * distributed under the License is distributed on an "AS IS" BASIS,
-- * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
-- * either express or implied.
-- * See the License for the specific language governing permissions and
-- * limitations under the License.
-- * 
-- * AUTHOR - Adam Bishop - ahbishop@uvic.ca
-- *       
-- * For comments or questions please contact the above e-mail address 
-- * OR    
-- * Ian Gable - igable@uvic.ca
-- *
-- *

connect 'jdbc:derby://localhost:1527/nimbus/WorkspacePersistenceDB;user=guest;password=*SANITIZED*;securityMechanism=8';
readonly on;
SELECT v.network, r.term_time, r.start_time, (r.start_time + d.min_duration*1000) as shutdown_time
FROM nimbus.vm_partitions as p, nimbus.vms as v, nimbus.vm_deployment as d, nimbus.resources as r
WHERE p.vmid = v.id AND v.id = d.vmid AND d.vmid = r.id AND r.state <> 6;
disconnect;
exit;

