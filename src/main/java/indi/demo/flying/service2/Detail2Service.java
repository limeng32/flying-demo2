package indi.demo.flying.service2;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper2.Detail2Mapper;
import indi.demo.flying.pojo.Detail2_;
import indi.demo.flying.pojo.LoginLogSource2;

@Service
public class Detail2Service implements Detail2Mapper {

	@Autowired
	private Detail2Mapper mapper;

	@Override
	public Detail2_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Detail2_ selectOne(Detail2_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Detail2_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(Detail2_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Detail2_> selectAll(Detail2_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Detail2_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Detail2_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Detail2_ t) {
		return mapper.count(t);
	}

	@Override
	public void loadLoginLogSource2(LoginLogSource2 loginLogSource2, Detail2_ detail2) {
		loginLogSource2.removeAllDetail2();
		detail2.setLoginLogSource2(loginLogSource2);
		loginLogSource2.setDetail2(mapper.selectAll(detail2));
	}

	@Override
	public void insertWithoutName(Detail2_ t) {
		mapper.insertWithoutName(t);
	}

	@Override
	public void insertWithoutFoo(Detail2_ t) {
		mapper.insertWithoutFoo(t);
	}

	@Override
	public int updateWithoutName(Detail2_ t) {
		return mapper.updateWithoutName(t);
	}

	@Override
	public int updatePersistentWithoutName(Detail2_ t) {
		return mapper.updatePersistentWithoutName(t);
	}

	@Override
	public Detail2_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}
}