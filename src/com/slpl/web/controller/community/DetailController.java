package com.slpl.web.controller.community;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slpl.web.entity.community.CommentReg;
import com.slpl.web.entity.community.CommentView;
import com.slpl.web.entity.community.Community;
import com.slpl.web.service.community.CommentRegService;
import com.slpl.web.service.community.CommunityService;

@WebServlet("/community/all/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int community_id = Integer.parseInt(request.getParameter("id"));
		int addCnt = 1;

		CommunityService service = new CommunityService();
		Community n = service.getDetail(community_id);
		service.updateCnt(community_id,addCnt);
		

		CommentRegService commentService = new CommentRegService();
		List<CommentView> c = commentService.getComment(community_id);

		request.setAttribute("n", n);
		request.setAttribute("comment", c);
		request.getRequestDispatcher("/community/all/detail.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int community_id = Integer.parseInt(request.getParameter("id"));

//		int hit_cnt = Integer.parseInt(request.getParameter("hitCnt"));
//		String title = request.getParameter("title");
		// title

//		System.out.println(title);

		String comment = request.getParameter("comment");

		System.out.println(community_id + comment);
		CommentRegService commentService = new CommentRegService();
		CommentReg cr = new CommentReg(comment, community_id);

		commentService.insert(cr);
		response.sendRedirect("detail?id=" + community_id);

	}

//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int community_id = Integer.parseInt(request.getParameter("id"));
//		
//		String comment = request.getParameter("comment");
//		
//		System.out.println(community_id + comment);
//		//int memberId = request.getParameter("member_")
//		
//		//String userId = sessio
////		String conten
//		
//		CommunityService service = new CommunityService();
//		CommentRegService commentService = new CommentRegService();
//		//CommentReg 
//		//CommunityService service = new CommunityService();
//		
//		Community n= service.getDetail(community_id);
//		List<CommentView> c = commentService.getComment(community_id);
//		CommentReg cr = new CommentReg(comment,community_id);
//		
//		commentService.insert(cr);
//		
////		CommentReg replyR = service.commentInsert(id,)
//		
//		
//		request.setAttribute("n", n);
//		request.setAttribute("comment", c);
//		request.getRequestDispatcher("/community/all/detail.jsp").forward(request, response);
//		
//		
//	}

}