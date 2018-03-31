package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import indi.demo.flying.pojoHelper.PojoSupport;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table
public class Role_ extends PojoSupport<Role_> implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.INTEGER, isUniqueKey = true, ignoreTag = { "noId" })
	private Integer id;

	@FieldMapperAnnotation(dbFieldName = "name", jdbcType = JdbcType.VARCHAR)
	private java.lang.String name;

	private java.util.Collection<Account_> account;

	private java.util.Collection<Account_> accountDeputy;

	public Integer getId() {
		return id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.util.Collection<Account_> getAccount() {
		if (account == null) {
			account = new java.util.LinkedHashSet<Account_>();
		}
		return account;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<Account_> getIteratorAccount() {
		if (account == null) {
			account = new java.util.LinkedHashSet<Account_>();
		}
		return account.iterator();
	}

	public void setAccount(java.util.Collection<Account_> newAccount) {
		removeAllAccount();
		for (java.util.Iterator<Account_> iter = newAccount.iterator(); iter.hasNext();) {
			addAccount((Account_) iter.next());
		}
	}

	public void addAccount(Account_ newAccount) {
		if (newAccount == null) {
			return;
		}
		if (this.account == null) {
			this.account = new java.util.LinkedHashSet<Account_>();
		}
		if (!this.account.contains(newAccount)) {
			this.account.add(newAccount);
			newAccount.setRole(this);
		} else {
			for (Account_ temp : this.account) {
				if (newAccount.equals(temp)) {
					if (temp != newAccount) {
						removeAccount(temp);
						this.account.add(newAccount);
						newAccount.setRole(this);
					}
					break;
				}
			}
		}
	}

	public void removeAccount(Account_ oldAccount) {
		if (oldAccount == null) {
			return;
		}
		if (this.account != null) {
			if (this.account.contains(oldAccount)) {
				for (Account_ temp : this.account) {
					if (oldAccount.equals(temp)) {
						if (temp != oldAccount) {
							temp.setRole((Role_) null);
						}
						break;
					}
				}
				this.account.remove(oldAccount);
				oldAccount.setRole((Role_) null);
			}
		}
	}

	public void removeAllAccount() {
		if (account != null) {
			Account_ oldAccount;
			for (java.util.Iterator<Account_> iter = getIteratorAccount(); iter.hasNext();) {
				oldAccount = (Account_) iter.next();
				iter.remove();
				oldAccount.setRole((Role_) null);
			}
			account.clear();
		}
	}

	public java.util.Collection<Account_> getAccountDeputy() {
		if (accountDeputy == null) {
			accountDeputy = new java.util.LinkedHashSet<Account_>();
		}
		return accountDeputy;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<Account_> getIteratorAccountDeputy() {
		if (accountDeputy == null) {
			accountDeputy = new java.util.LinkedHashSet<Account_>();
		}
		return accountDeputy.iterator();
	}

	public void setAccountDeputy(java.util.Collection<Account_> newAccountDeputy) {
		removeAllAccountDeputy();
		for (java.util.Iterator<Account_> iter = newAccountDeputy.iterator(); iter.hasNext();) {
			addAccountDeputy((Account_) iter.next());
		}
	}

	public void addAccountDeputy(Account_ newAccountDeputy) {
		if (newAccountDeputy == null) {
			return;
		}
		if (this.accountDeputy == null) {
			this.accountDeputy = new java.util.LinkedHashSet<Account_>();
		}
		if (!this.accountDeputy.contains(newAccountDeputy)) {
			this.accountDeputy.add(newAccountDeputy);
			newAccountDeputy.setRoleDeputy(this);
		} else {
			for (Account_ temp : this.accountDeputy) {
				if (newAccountDeputy.equals(temp)) {
					if (temp != newAccountDeputy) {
						removeAccountDeputy(temp);
						this.accountDeputy.add(newAccountDeputy);
						newAccountDeputy.setRole(this);
					}
					break;
				}
			}
		}
	}

	public void removeAccountDeputy(Account_ oldAccountDeputy) {
		if (oldAccountDeputy == null) {
			return;
		}
		if (this.accountDeputy != null) {
			if (this.accountDeputy.contains(oldAccountDeputy)) {
				for (Account_ temp : this.accountDeputy) {
					if (oldAccountDeputy.equals(temp)) {
						if (temp != oldAccountDeputy) {
							temp.setRole((Role_) null);
						}
						break;
					}
				}
				this.accountDeputy.remove(oldAccountDeputy);
				oldAccountDeputy.setRoleDeputy((Role_) null);
			}
		}
	}

	public void removeAllAccountDeputy() {
		if (accountDeputy != null) {
			Account_ oldAccountDeputy;
			for (java.util.Iterator<Account_> iter = getIteratorAccountDeputy(); iter.hasNext();) {
				oldAccountDeputy = (Account_) iter.next();
				iter.remove();
				oldAccountDeputy.setRoleDeputy((Role_) null);
			}
			accountDeputy.clear();
		}
	}
}
