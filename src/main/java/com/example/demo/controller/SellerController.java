package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Seller;
import com.example.demo.dao.SellerDao;
import com.example.demo.urlUtil.UrlConnection;

@RestController
public class SellerController {

	@Autowired
	private SellerDao sellerDao;

	@GetMapping("/page/{statu}/{page}")
	public List<Seller> insert(@PathVariable("statu")String statu,@PathVariable("page") Integer page) throws Exception {
		
		return sellerDao.save(
				UrlConnection.trToSeller(
				  UrlConnection.getTr(
					UrlConnection.getContent(
					  UrlConnection.getURL(statu, page)
					  )
					)
				  )
				);
	}

}
