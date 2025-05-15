package com.example.tryoutpas_06_12;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.tryoutpas_06_12.TeamResponse;


public interface APIService {
    @GET("search_all_teams.php")
    Call<TeamResponse> getTeams(@Query("l") String league);
}
