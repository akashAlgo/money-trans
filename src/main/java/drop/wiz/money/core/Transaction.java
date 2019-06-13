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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ar
 * Jun 12, 2019
 */
@Entity(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

	private static final long serialVersionUID = -3280962249163675763L;

	@Id
	private Long id;
	
	@EmbeddedId
	private Account sourceAccount;
	
	@EmbeddedId
	private Account destinationAccount;
	
	@Column
	private BigDecimal amount;
	
	@Column(name = "conversion_factor")
	private Double conversionFactor;
	
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
}
