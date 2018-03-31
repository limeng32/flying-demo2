package indi.demo.flying.service2;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper2.Account2Mapper;
import indi.demo.flying.pojo.Account2_;
import indi.demo.flying.pojo.Role2_;

@Service
public class Account2Service implements Account2Mapper {

	@Autowired
	private Account2Mapper mapper;

	@Override
	public Account2_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Account2_ selectOne(Account2_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Account2_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(Account2_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Account2_> selectAll(Account2_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Account2_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Account2_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Account2_ t) {
		return mapper.count(t);
	}

	@Override
	public void loadRole2(Role2_ role2_, Account2_ account2_) {
		role2_.removeAllAccount();
		account2_.setRole(role2_);
		role2_.setAccount(mapper.selectAll(account2_));
	}

	@Override
	public Account2_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}

}