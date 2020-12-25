package com.slpl.web.service.community;

import java.util.List;

import com.slpl.web.dao.community.CommentRegDao;
import com.slpl.web.dao.community.CommunityDao;
import com.slpl.web.dao.jdbc.JdbcCommentDao;
import com.slpl.web.dao.jdbc.JdbcCommunityDao;
import com.slpl.web.entity.community.CommentView;
import com.slpl.web.entity.community.Community;
import com.slpl.web.entity.community.CommunityView;

public class CommunityService {
	
	private CommunityDao communityDao;
	
	
	public CommunityService() {
		communityDao = new JdbcCommunityDao();
		
	}
	
	public List<Community> getList() {
		return communityDao.getList();
	}

	public Community getDetail(int id) {
		return communityDao.get(id);
	}

	public int insert(Community community) {
		
		return communityDao.insert(community);
	}	
	

	public int update(Community community) {
		
		return communityDao.update(community);
	}

	public int delete(int id) {
		
		return communityDao.delete(id);
	}

	public List<CommunityView> getViewList() {
		String field = "title";
		String query = "";
		int page = 1;
		String category = "전체게시판";
		return communityDao.getViewList(field, query,page,category);
	}
	
	public List<CommunityView> getViewList(int page){
		String field = "title";
		String query = "";
		String category = "전체게시판";
		return communityDao.getViewList(field, query,page,category);
	}

	public List<CommunityView> getViewList(String field, String query, int page) {
		String category = "전체게시판";
		return communityDao.getViewList(field, query,page,category);
	}
	
	public int getCommunityCount(String field, String query, String category) {
		return communityDao.getCommunityCount(field, query,category);
	}

	public List<CommunityView> getViewList(String field, String query, int page, String category) {
		return communityDao.getViewList(field, query,page,category);
	}

	public int updateCnt(int community_id, int addCnt) {
		return communityDao.updateCnt(community_id,addCnt);
	}



	public int updateRecommend(int community_id, int recommend_cnt) {
		return communityDao.updateRecommend(community_id,recommend_cnt);
		
	}


	

//	public int getLastId() {
//		
//		Community c = CommunityDao.getLast();
//		return c.getId();
//	}

}