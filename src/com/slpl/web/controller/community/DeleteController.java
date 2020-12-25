package com.slpl.web.controller.community;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slpl.web.service.community.CommunityService;

@WebServlet("/community/all/delete")
public class DeleteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		CommunityService service = new CommunityService();
		int result = service.delete(id);

		String category = request.getParameter("c");
		String categoryName = URLEncoder.encode(category);

		response.sendRedirect("/community/all/list?c=" + categoryName);

	}

}