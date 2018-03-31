package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.DetailMapper;
import indi.demo.flying.pojo.Detail_;
import indi.demo.flying.pojo.LoginLog_;

@Service
public class DetailService implements DetailMapper {

	@Autowired
	private DetailMapper mapper;

	@Override
	public Detail_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Detail_ selectOne(Detail_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Detail_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(Detail_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Detail_> selectAll(Detail_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Detail_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Detail_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Detail_ t) {
		return mapper.count(t);
	}

	@Override
	public void loadLoginLog(LoginLog_ loginlog, Detail_ detail) {
		loginlog.removeAllDetail();
		detail.setLoginLog(loginlog);
		loginlog.setDetail(mapper.selectAll(detail));
	}

	@Override
	public Detail_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}
}