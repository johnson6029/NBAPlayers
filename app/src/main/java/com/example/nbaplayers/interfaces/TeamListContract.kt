package com.example.nbaplayers.interfaces

import com.example.nbaplayers.Events
import com.example.nbaplayers.models.Team

interface TeamListContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(teamsList: ArrayList<Team>?)
            fun onFailure(t: Throwable?)
        }

        fun getMovieList(onFinishedListener: OnFinishedListener?)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun setDataToRecyclerView(teamsList: ArrayList<Team>?)
        fun onResponseFailure(throwable: Throwable?)
        fun filterTeams(teamsList: List<Team>?)
    }

    interface Presenter {
        fun onDestroy()
        fun requestDataFromServer()
        fun filterTeams(events: Events)
    }
}
