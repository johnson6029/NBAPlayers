package com.example.nbaplayers

import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.FilterTypes

object LocalDataStorage {
    // properties
    var teamsList: ArrayList<Team> = arrayListOf()

    //helpers
    fun getTeamsDataById(id: Int): Team {
        return teamsList.filter { it.id == id }.map { it }.first()
    }
}