package com.kittendev.tieba.api;

import retrofit2.http.GET;

import retrofit2.Call;
import java.util.List;
import retrofit2.http.Path;

public interface TieBaService {
	
	@GET("/")
	Call<List<Object>> listRepos()
	
}
