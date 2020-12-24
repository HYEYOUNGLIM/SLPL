package com.slpl.web.controller.admin.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.slpl.web.entity.community.CommunityCategory;
import com.slpl.web.entity.member.Admin;
import com.slpl.web.entity.member.Member;
import com.slpl.web.entity.member.MemberView;
import com.slpl.web.service.member.AdminService;
import com.slpl.web.service.member.MemberService;


@WebServlet("/admin/member/detail")
@MultipartConfig(        
	    fileSizeThreshold=1024*1024, 
	    maxFileSize=1024*1024*5, 
	    maxRequestSize=1024*1024*5*5)
public class DetailController extends HttpServlet {
	
	MemberService service;
	AdminService adminService;
	
	public DetailController() {
		service = new MemberService();
		adminService = new AdminService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("id : "+request.getParameter("id"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		MemberView m = service.getView(id);
		
		
		// 카테고리 목록 가져오기
		List<CommunityCategory> categorys = new ArrayList<CommunityCategory>();
		categorys.add(new CommunityCategory(1, "민슈찍", 6));
		categorys.add(new CommunityCategory(2, "반민슈찍",3));
		categorys.add(new CommunityCategory(3, "민팥찍", 1));
		categorys.add(new CommunityCategory(4, "반민팥찍", 4));
		categorys.add(new CommunityCategory(5, "민슈부",5));
		categorys.add(new CommunityCategory(6, "반민슈부",1));
		categorys.add(new CommunityCategory(7, "민팥부",4));
		categorys.add(new CommunityCategory(8, "반민팥부",3));
			
		// 이메일 처리
		List<String> addrList = new ArrayList<String>(); 
		addrList.add("naver.com");
		addrList.add("daum.net");
		addrList.add("hanmail.net"); 
		addrList.add("gmail.com");
		
		String[] emailStrs = m.getEmail().split("@");  
		int result = addrList.indexOf(emailStrs[1]);
		if(result < 0 ) {
			request.setAttribute("isCustomAddr", true);
		} else {
			request.setAttribute("isCustomAddr", false);
		}			

		request.setAttribute("emailId", emailStrs[0]);
		request.setAttribute("emailAddress", emailStrs[1]);
		
		request.setAttribute("categorys", categorys);
		request.setAttribute("member", m);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String loginId = request.getParameter("loginId");
		String pw= request.getParameter("pw");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String phoneNumber = request.getParameter("phoneNumber");
		
		String emailId = request.getParameter("emailId");
		String emailAddress = request.getParameter("emailAddress");
		if(emailAddress.equals("none") == true) {
			emailAddress = request.getParameter("customAddress");
		}
		String email = emailId + "@" + emailAddress;
		
		String gender = request.getParameter("gender");
		int categoryId = Integer.parseInt(request.getParameter("category"));   //선택된 카테고리 id 반환
		System.out.println("gender : "+gender);
		
		String yearStr = request.getParameter("year");
		String monthStr = request.getParameter("month");
		String dayStr = request.getParameter("day");
		Date birthday = null;   // Date형식으로 변환
		if(yearStr.equals("") == false && monthStr.equals("") == false && dayStr.equals("") == false ) {
			int year = Integer.parseInt(yearStr);
			int month = Integer.parseInt(monthStr);
			int day = Integer.parseInt(dayStr);
			String birthdayStr = String.format("%s-%s-%s", year, month, day);
			birthday = Date.valueOf(birthdayStr);   // Date형식으로 변환
		}
		
		
		String regdateStr = request.getParameter("regdate");
		Timestamp regdate = Timestamp.valueOf(regdateStr);     // Date형식으로 변환
		
		Part filePart = request.getPart("profile");
		String profileImgName = "default.png";
		if(filePart != null && filePart.getSize() > 0) {   // 파일 첨부 input이 여러개면 다 name을 file로 하면 한번에 갖고와짐
			System.out.println("name : "+filePart.getName()+ " / fileName : "+filePart.getSubmittedFileName());

			profileImgName = filePart.getSubmittedFileName();
            
			// 경로 받아오기
			String pathTemp = request.getServletContext().getRealPath("/static/member/2020/"+id+"/");   // 실제 서블릿 있는 경로를 알아내고, 인자로 넣은 경로의 실제 물리경로를 알아내주는 녀석.
			
			// 디렉토리 만들기
			File path = new File(pathTemp);
			if(!path.exists()) {
				path.mkdirs();		// s 붙은 놈은 저 경로중 없는 폴더를 모두 만들고, 안붙은 놈은 끝 폴더만 만듬	
			}

			// 파일 저장 디렉토리 : /static/member/2020/?(아이디)/ 
			// 파일 저장은 톰캣 디렉토리에 됨.(동적으로 만들어진건 다 저기에 되는듯)
			String filePath = pathTemp + File.separator + profileImgName;
			System.out.println("filePath : "+filePath);  // 전송된 파일의 이름 갖고오기
		
			InputStream fis = filePart.getInputStream();
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}

			
			fos.close();
			fis.close();
		}

		String authority = request.getParameter("authority");
		// authority 판단해서 amdin에 insert rmsep w
		if(authority.equals("admin") == true) {  // admin 선택
			Admin admin = adminService.get(id); 
			if(admin == null) {     // admin 테이블에 없을 경우만 insert. 있는데 admin으로 체크 된거면 굳이 할 필요 X
				admin = new Admin(id);
				adminService.insert(admin); // 중복 생각해야함
			}
		} else {   // user 선택
			Admin admin = adminService.get(id); 
			if(admin != null) {     // user로 했는데 admin 테이블에 있을 경우 delete
				admin = new Admin(id);
				adminService.delete(id); // 중복 생각해야함
			}
		}
		
		Member member = new Member(id, loginId, pw, name, nickname, gender, birthday, 
				phoneNumber, email, regdate, profileImgName, categoryId);
		service.update(member);
		response.sendRedirect("list");
		
	}
	
}
