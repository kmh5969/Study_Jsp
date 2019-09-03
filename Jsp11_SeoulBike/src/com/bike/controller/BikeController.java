package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/bike.do")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BikeController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+ command +"]");
		BikeDao dao = new BikeDao();
		PrintWriter out = response.getWriter();
		
		if(command.equals("first")) {
			
			response.sendRedirect("bike01.jsp");
			
		} else if(command.equals("first_db")) {
			
			String[] tmp = request.getParameterValues("bike");
			List<BikeDto> list = new ArrayList<BikeDto>();
			
			for(int i = 0; i < tmp.length; i++) {
				
				BikeDto dto = new BikeDto();
				String[] dtoVal = tmp[i].split("/");
				
				dto.setRentid(dtoVal[0]);
				dto.setAddrgu(dtoVal[1]);
				dto.setContentid(Integer.parseInt(dtoVal[2]));
				dto.setContentnm(dtoVal[3]);
				dto.setNewaddr(dtoVal[4]);
				dto.setCradlecount(Integer.parseInt(dtoVal[5]));
				dto.setLongitude(Double.parseDouble(dtoVal[6]));
				dto.setLatitude(Double.parseDouble(dtoVal[7]));
				
				list.add(dto);
			}
			
			int res = dao.insert(list);
			System.out.println(res);
			
		} else if(command.equals("second")) {
			
			response.sendRedirect("bike02.jsp");
			
		} else if(command.equals("second_db")) {
			
			String obj = request.getParameter("obj");
//			System.out.println("obj");
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(obj);
			List<BikeDto> list = new ArrayList<BikeDto>();
			
			for(int i = 0; i < element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				//System.out.println(element.getAsJsonObject().get("DATA").getAsJsonArray().get(i));
				
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				
				JsonElement rent_id_je = tmp.get("rent_id");
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String rent_id = rent_id_je.getAsString();
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				//System.out.println(rent_id + addr_gu + content_id + content_nm + new_addr + cradle_count + longitude + latitude);
				
				BikeDto dto = new BikeDto(rent_id, addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				
				list.add(dto);
			}
			
			int res = dao.insert(list);
			out.println(res);
		}
	}

}
