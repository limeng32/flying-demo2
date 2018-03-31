package indi.demo.flying.service2;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper2.LoginLogSource2Mapper;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.LoginLogSource2;

@Service
public class LoginLogSource2Service implements LoginLogSource2Mapper {

	@Autowired
	private LoginLogSource2Mapper mapper;

	@Override
	public LoginLogSource2 select(Object id) {
		return mapper.select(id);
	}

	@Override
	public LoginLogSource2 selectOne(LoginLogSource2 t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(LoginLogSource2 t) {
		mapper.insert(t);
	}

	@Override
	public int update(LoginLogSource2 t) {
		return mapper.update(t);
	}

	@Override
	public Collection<LoginLogSource2> selectAll(LoginLogSource2 t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(LoginLogSource2 t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(LoginLogSource2 t) {
		return mapper.delete(t);
	}

	@Override
	public int count(LoginLogSource2 t) {
		return mapper.count(t);
	}

	@Override
	public void loadAccount(Account_ account, LoginLogSource2 loginLogSource2) {
		account.removeAllLoginLogSource2();
		loginLogSource2.setAccount(account);
		account.setLoginLogSource2(mapper.selectAll(loginLogSource2));
	}

	@Override
	public LoginLogSource2 selectWithoutAccount(Object id) {
		return mapper.selectWithoutAccount(id);
	}

	@Override
	public int updateNoFlush(LoginLogSource2 t) {
		return mapper.updateNoFlush(t);
	}

	@Override
	public LoginLogSource2 selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}
}