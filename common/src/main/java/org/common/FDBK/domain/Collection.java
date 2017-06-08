package org.common.FDBK.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.common.RBAC.domain.Account;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_collection")
public class Collection {
	@Id
	@Column(name = "pk_col_id")
	// 该主键生成器名为uuid，使用Hibernate的uuid策略，
	@GenericGenerator(name = "uuid", strategy = "uuid")
	// 指定使用fk_hilo主键生成器
	@GeneratedValue(generator = "uuid")
	private String id;
	@ManyToOne(targetEntity = Store.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_store", referencedColumnName = "fk_sto_id")
	private Store store;
	@ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_customer", referencedColumnName = "pk_cus_id")
	private Account account;
	@Column(name = "col_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
