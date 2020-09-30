package com.example.nbaplayers.api

import com.example.nbaplayers.models.Team
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("nba-team-viewer/master/input.json")
    fun getTeamData(): Call<ArrayList<Team>>
}