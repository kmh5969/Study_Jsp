package com.my.biz;

import com.my.dao.MyLoginDao;
import com.my.dto.MyLoginDto;

public class MyLoginBiz {
	
	MyLoginDao dao = new MyLoginDao();
	
	public MyLoginDto login(String myid, String mypw) {
		
		return dao.login(myid, mypw);
	}

}
