/**
 * 
 */
package drop.wiz.money.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ar
 * Jun 12, 2019
 */
@Entity(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 8842051556850562089L;

	@Column
	private Long id;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "account_type")
	private AccountType accountType;
	
	@Column
	private Currency currency;
	
	@Column(name = "user_id")
	private String userId;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
}
