package com.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.dto.PaginationDTO;
import com.test.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	
	//抓兩個ＤＴＯ回傳
	public Map<String, Object> fetchUsers() {
		String url = "https://gorest.co.in/public-api/users";

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			JSONObject json = new JSONObject(response.getBody());
			
			// users
			JSONArray dataArray = json.getJSONArray("data");
			List<UserDTO> users = new ArrayList<>();
			
			for (int i = 0; i < dataArray.length(); i++) {
				JSONObject userJson = dataArray.getJSONObject(i);
				UserDTO user = new UserDTO();
				user.setId(userJson.getInt("id"));
				user.setName(userJson.getString("name"));
				user.setEmail(userJson.getString("email"));
				user.setGender(userJson.getString("gender"));
				user.setStatus(userJson.getString("status"));
				users.add(user);
			}
			
			// paginations
			JSONObject paginationJson = json.getJSONObject("meta").getJSONObject("pagination");
	        PaginationDTO pagination = new PaginationDTO();
	        pagination.setPage(paginationJson.getInt("page"));
	        pagination.setPages(paginationJson.getInt("pages"));
	        pagination.setTotal(paginationJson.getInt("total"));
	        pagination.setLimit(paginationJson.getInt("limit"));
	        
	        Map<String, Object> result = new HashMap<>();
	        result.put("users", users);
	        result.put("pagination", pagination);
	        
	        return result;
		} catch (Exception e) {
			throw new RuntimeException("解析 JSON 失敗: " + e.getMessage());
		}
	}
}