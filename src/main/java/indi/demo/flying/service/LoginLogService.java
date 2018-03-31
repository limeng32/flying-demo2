package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.LoginLogMapper;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.LoginLog_;

@Service
public class LoginLogService implements LoginLogMapper {

	@Autowired
	private LoginLogMapper mapper;

	@Override
	public LoginLog_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public LoginLog_ selectOne(LoginLog_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(LoginLog_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(LoginLog_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<LoginLog_> selectAll(LoginLog_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(LoginLog_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(LoginLog_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(LoginLog_ t) {
		return mapper.count(t);
	}

	@Override
	public void loadAccount(Account_ account, LoginLog_ loginLog) {
		account.removeAllLoginLog();
		loginLog.setAccount(account);
		account.setLoginLog(mapper.selectAll(loginLog));
	}

	@Override
	public LoginLog_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}
}