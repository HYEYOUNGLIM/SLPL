package com.slpl.web.controller.admin.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slpl.web.entity.member.Member;
import com.slpl.web.service.member.MemberService;

@WebServlet("/admin/common/profile")
public class ProfileController extends HttpServlet {
	
	MemberService service;
	
	public ProfileController() {
		service = new MemberService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imgName = "default.png";
		String idParam = request.getParameter("id");		// 유저의 아이디
		
		int id = 0;
		Member m = null;
		if(idParam != null) {
			id = Integer.parseInt(idParam);
			m = service.get(id);
			imgName = m.getProfileImg();
		} else {
			return;
		}
		
		// 경로 받아오기
		String pathTemp = request.getServletContext().getRealPath("/assets/images/common/");   // 기본 이미지(기본 이미지인 경우 : 프로필 등록한적X, 초기화)
		if(imgName.equals("default.png") == false) {
			pathTemp = request.getServletContext().getRealPath("/static/member/2020/"+id+"/");   // 실제 서블릿 있는 경로를 알아내고, 인자로 넣은 경로의 실제 물리경로를 알아내주는 녀석.
		} 
		
		// 디렉토리 찾기
		File path = new File(pathTemp);
		
		if(path.exists()) {   // 이미지 폴더가 있으면
			// 파일 저장은 톰캣 디렉토리에 됨.(동적으로 만들어진건 다 저기에 되는듯)
			String filePath = pathTemp + File.separator + imgName;
			//System.out.println("filePath : "+filePath);  // 전송된 파일의 이름 갖고오기
		
			FileInputStream fis = new FileInputStream(filePath);    // 경로에 있는 파일을 읽어옴
			OutputStream fos = response.getOutputStream();			// 저장과는 반대로 출력임
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = fis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			
			fos.close();
			fis.close();
		} else {
			System.out.println("path.exists() 없어");
		}
	}

}
