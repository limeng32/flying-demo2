package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.Account22Mapper;
import indi.demo.flying.pojo.Account22;
import indi.demo.flying.pojo.Role2_;

@Service
public class Account22Service implements Account22Mapper {

	@Autowired
	private Account22Mapper mapper;

	@Override
	public Account22 select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Account22 selectOne(Account22 t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Account22 t) {
		mapper.insert(t);
	}

	@Override
	public int update(Account22 t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Account22> selectAll(Account22 t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Account22 t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Account22 t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Account22 t) {
		return mapper.count(t);
	}

	@Override
	public void loadRole2(Role2_ role2_, Account22 account22) {
		role2_.removeAllAccount2();
		account22.setRole(role2_);
		role2_.setAccount2(mapper.selectAll(account22));
	}

	@Override
	public Account22 selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}

}