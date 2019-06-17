package drop.wiz.money.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: arastogi
 */

@Entity(name = "Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "transaction")
@NamedQueries({@NamedQuery(name = "transaction.bySourceAccNumber",
		query = "select t from Transaction t where t.sourceAccount.id = :accNumber "),
		@NamedQuery(name = "transaction.byDestinationAccNumber",
				query = "select t from Transaction t where t.destinationAccount.id = :accNumber ")})
public class Transaction implements Serializable {

	private static final long serialVersionUID = -3280962249163675763L;

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue
	@JsonProperty
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_account", referencedColumnName = "id")
	@JsonProperty("source_account")
	private Account sourceAccount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_account", referencedColumnName = "id")
	@JsonProperty("destination_account")
	private Account destinationAccount;

	@Column
	@JsonProperty
	private BigDecimal amount;

	@Column(name = "conversion_factor")
	@JsonProperty("conversion_factor")
	private Double conversionFactor;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

}
