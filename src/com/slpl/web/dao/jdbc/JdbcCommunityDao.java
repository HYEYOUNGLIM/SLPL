package com.slpl.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.slpl.web.dao.community.CommunityDao;
import com.slpl.web.entity.community.CommentReg;
import com.slpl.web.entity.community.Community;
import com.slpl.web.entity.community.CommunityView;

public class JdbcCommunityDao implements CommunityDao {

	@Override
	public int insert(Community community) {
		int result = 0;
		String url = DBContext.URL;
		String sql = "INSERT INTO community(id,member_id,TITLE,CONTENT,category_id) VALUES(seq.nextval,2,?,?,?)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, community.getTitle());
			st.setString(2, community.getContent());
			st.setInt(3, community.getCategoryId());
			System.out.println("==================================="+community.getCategory());

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	@Override
	public int update(Community community) {
		int result = 0;
		String url = DBContext.URL;
		String sql = "update community set title = ?, content = ? WHERE id = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, community.getTitle());
			st.setString(2, community.getContent());
			st.setInt(3, community.getId());

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	@Override
	public int delete(int id) {
		int result = 0;
		String url = DBContext.URL;
		String sql = "delete from community where id = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CommunityView get(int id) {
		CommunityView n = null;

		String url = DBContext.URL;
		String sql = "select * from community_view where id = " + id;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			int i = 0;
			if (rs.next()) {

				id = rs.getInt("id");
				int memberId = rs.getInt("member_id");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int recommendCnt = rs.getInt("recommend_cnt");
				int hitCnt = rs.getInt("hit_cnt");
				String imgs = "";
				Date regDate = rs.getDate("regdate");
				String memberNick = rs.getString("member_nick");
				int commentCnt = rs.getInt("comment_cnt");
				hitCnt++;

				n = new CommunityView(id, memberId, categoryId, title, content, recommendCnt, hitCnt, imgs, regDate,
						memberNick, commentCnt);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(n);
		return n;
	}

	@Override
	public List<Community> getList() {
		List<Community> list = new ArrayList<>();

		String url = DBContext.URL;
		String sql = "select * from community";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				int memberId = rs.getInt("member_id");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int recommendCnt = rs.getInt("recommend_cnt");
				int hitCnt = rs.getInt("hit_cnt");
				String imgs = rs.getString("imgs");
				Date regDate = rs.getDate("regdate");

				Community n = new Community(id, memberId, categoryId, title, content, recommendCnt, hitCnt, imgs,
						regDate);
				System.out.println(n);
				list.add(n);

			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<CommunityView> getViewList() {

		int page = 1;
		String field = "";
		String query = "";
		String category = "전체게시판";
		return getViewList(field, query, page, category);

		// return null;
	}

	@Override
	public Community getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommunityView> getViewList(String field, String query, int page, String category) {
		List<CommunityView> list = new ArrayList<>();

		String url = DBContext.URL;
		String sql = "select  * from (select rownum num,n.* \r\n" + "from (select * from community_view where " + field
				+ " like ? and  name = ? order by regdate desc) n)\r\n" + "where num between ? and ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			// st.setS
			st.setString(1, "%" + query + "%");
			st.setString(2, category);
			st.setInt(3, 1 + (page - 1) * 5);
			st.setInt(4, page * 5);
			ResultSet rs = st.executeQuery();
			System.out.println(category);
			while (rs.next()) {
				int num = rs.getInt("num");
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int recommendCnt = rs.getInt("recommend_cnt");
				int hitCnt = rs.getInt("hit_cnt");
				Date regDate = rs.getDate("regdate");
				int commentCnt = rs.getInt("comment_cnt");
				String memberName = rs.getString("member_name");
				int categoryId = rs.getInt("category_id");
				category = rs.getString("name");

				String memberNick = rs.getString("member_nick");
//				String noticeContent = rs.getString("notice_content");
//				String noticeTitle = rs.getString("notice_title");
//				Date noticeDate = rs.getDate("notice_regdate");

				CommunityView n = new CommunityView(id, title, recommendCnt, hitCnt, regDate, commentCnt, memberName,
						categoryId, memberNick, category,num);
				System.out.println(category);
				list.add(n);

			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int getCommunityCount(String field, String query, String category) {

		int count = 0;

		String url = DBContext.URL;
		String sql = "select count(id) count from (select rownum num,n.* \r\n"
				+ "from (select * from community_view where " + field
				+ " like ? and name = ? order by regdate desc) n)\r\n";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			// st.setS
			st.setString(1, "%" + query + "%");
			st.setString(2, category);
			ResultSet rs = st.executeQuery();

			if (rs.next())
				count = rs.getInt("count");

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public Community getNextNotice(int id) {
		Community n = null;

		String url = DBContext.URL;
		String sql = "select * from community_view\r\n" + "		where id = (\r\n"
				+ "		select id from community\r\n"
				+ "		where regdate > (select regdate from community where id = ?)\r\n" + "		and rownum = 1\r\n"
				+ "		)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				id = rs.getInt("id");
				int memberId = rs.getInt("member_id");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int recommendCnt = rs.getInt("recommend_cnt");
				int hitCnt = rs.getInt("hit_cnt");
				String imgs = rs.getString("imgs");
				Date regDate = rs.getDate("regdate");

				n = new Community(id, memberId, categoryId, title, content, recommendCnt, hitCnt, imgs, regDate);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(n);
		return n;

	}

	public Community getPrevNotice(int id) {
		Community n = null;

		String url = DBContext.URL;

		String sql = "select id from (select * from community_view order by regdate desc)\r\n"
				+ "where regdate < (select regdate from community where id = ?)\r\n" + "and rownum = 1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				id = rs.getInt("id");
				int memberId = rs.getInt("member_id");
				int categoryId = rs.getInt("category_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int recommendCnt = rs.getInt("recommend_cnt");
				int hitCnt = rs.getInt("hit_cnt");
				String imgs = rs.getString("imgs");
				Date regDate = rs.getDate("regdate");

				n = new Community(id, memberId, categoryId, title, content, recommendCnt, hitCnt, imgs, regDate);
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(n);
		return n;

	}

	@Override
	public List<CommunityView> getViewList(int page) {

		String field = "";
		String query = "";
		String category = "전체게시판";
		return getViewList(field, query, page, category);
		// return null;
	}

	@Override
	public int commentInsert(CommentReg commentReg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comentDelete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommunityView> getViewList(String field, String query, int page) {

		String category = "전체게시판";
		return getViewList(field, query, page, category);
	}

	@Override
	public int updateCnt(int community_id, int addCnt) {
		int result = 0;
		String url = DBContext.URL;
		String sql = "update community set hit_cnt = hit_cnt+" + addCnt + "  WHERE id = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, community_id);

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	@Override
	public int updateRecommend(int community_id, int recommendCnt) {
		int result = 0;
		String url = DBContext.URL;
		String sql = "update community set recommend_cnt = recommend_cnt+" + recommendCnt + "  WHERE id = ?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, DBContext.UID, DBContext.PWD);
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, community_id);

			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

//	@Override
//	public int getCommunityCount(String field, String query, String category) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public int getLast() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
