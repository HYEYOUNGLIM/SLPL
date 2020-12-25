package com.slpl.web.service.test;

import java.util.List;

import com.slpl.web.dao.jdbc.JdbcTestListViewDao;
import com.slpl.web.dao.test.TestListViewDao;
import com.slpl.web.entity.test.Test;
import com.slpl.web.entity.test.TestListView;

public class TestListViewService {

	private TestListViewDao testListViewDao;

	public TestListViewService() {
		testListViewDao = new JdbcTestListViewDao();
	}

	public List<TestListView> getList(int page, int pageLength, String field, String query, String align, String order) {

		int startIndex = 1 + (page - 1) * pageLength;
		int endIndex = page * pageLength;

		return testListViewDao.getList(startIndex, endIndex, field, query, align, order);
	}
	
	public List<TestListView> bestList() {

		return testListViewDao.bestList();
	}

	public List<TestListView> myList(int page, int pageLength, String field, String query, String align, int writerId) {

		int startIndex = 1 + (page - 1) * pageLength;
		int endIndex = page * pageLength;

		return testListViewDao.myList(startIndex, endIndex, field, query, align, writerId);
	}

	public TestListView get(int id) {

		return testListViewDao.get(id);
	}
}
