package com.example.nbaplayers.api

import com.example.nbaplayers.interfaces.TeamListContract
import com.example.nbaplayers.models.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsListModel : TeamListContract.Model {
    override fun getTeamsList(onFinishedListener: TeamListContract.Model.OnFinishedListener?) {
        val apiService: ApiInterface = ApiClient().retrofitClient.create(ApiInterface::class.java)
        val call = apiService.getTeamData()

        call.enqueue(object : Callback<ArrayList<Team>> {
            override fun onResponse(call: Call<ArrayList<Team>>, response: Response<ArrayList<Team>>) {
                val teams: ArrayList<Team> = response.body()!!
                onFinishedListener?.onFinished(teams)
            }
            override fun onFailure(call: Call<ArrayList<Team>>, t: Throwable) {
                onFinishedListener?.onFailure(t)
            }
        })
    }
}
