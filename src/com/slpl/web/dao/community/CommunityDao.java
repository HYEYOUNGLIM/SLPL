package com.slpl.web.dao.community;

import java.util.List;

import com.slpl.web.entity.community.CommentReg;
import com.slpl.web.entity.community.Community;
import com.slpl.web.entity.community.CommunityView;

public interface CommunityDao {

	int insert(Community community);
	int update(Community community);
	int delete(int id);
	int commentInsert(CommentReg commentReg);
	int comentDelete(int id);
	Community getLast();
	
	Community get(int id);
	List<Community> getList();
	List<CommunityView> getViewList();
	List<CommunityView> getViewList(int page);
	List<CommunityView> getViewList(String field, String query, int page);
	int getCommunityCount(String field, String query);


}