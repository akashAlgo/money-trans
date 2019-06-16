/**
 * 
 */
package drop.wiz.money.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ar
 * Jun 12, 2019
 */
@Entity(name = "Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "account")
@NamedQueries({@NamedQuery(name = "account.byUserId", query = "select a from Account a where a.userId like :userId ")})
public class Account implements Serializable {

	private static final long serialVersionUID = 8842051556850562089L;

	@Column(name = "id", updatable = false, nullable = false)
	@Id
	@GeneratedValue
	@JsonProperty
	private Long id;

	@Column(name = "is_active")
	@JsonProperty("is_active")
	private Boolean isActive;

	@Column(name = "account_type")
	@JsonProperty("account_type")
	@Builder.Default
	private AccountType accountType = AccountType.CURRENT;

	@Column
	@JsonProperty
	private Currency currency;

	@Column(name = "balance")
	@JsonProperty
	@Builder.Default
	private BigDecimal balance = BigDecimal.ZERO;

	@Column(name = "user_id")
	@JsonProperty("user_id")
	private String userId;

	@JsonIgnore
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@JsonIgnore
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

}
