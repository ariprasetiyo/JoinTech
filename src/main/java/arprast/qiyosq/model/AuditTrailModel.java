
package arprast.qiyosq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import arprast.qiyosq.ref.ActionType;

@Entity
@Table(name ="sys_audit_trail")
public class AuditTrailModel extends ModelEntity {
    
	private static final long serialVersionUID = 2432434267482377275L;
	
	@Column(name = "action_type")
    ActionType actionType;
    
    
}
