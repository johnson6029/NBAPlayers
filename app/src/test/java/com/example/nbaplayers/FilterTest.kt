package com.example.nbaplayers

import com.example.nbaplayers.models.Team
import com.example.nbaplayers.utils.FilterTeams
import com.example.nbaplayers.utils.FilterTypes
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FilterTest{
   private var teamsList: ArrayList<Team> = arrayListOf()
    @Before
    fun setUpTestData(){
        setUpFilterData()
    }

    @Test
    fun filterWinsTest(){
        val filterTeams = FilterTeams(teamsList)
        val filterList = filterTeams.filterTeams(events = FilterTypes.sortedByWins)
        Assert.assertEquals(teamsList[0].wins,filterList[1].wins)
    }

    @Test
    fun filterLossesTest(){
        val filterTeams = FilterTeams(teamsList)
        val filterList = filterTeams.filterTeams(events = FilterTypes.sortedByLosses)
        Assert.assertEquals(teamsList[0].losses,filterList[2].losses)
    }

    @Test
    fun filterByAlphabetsTest(){
        val filterTeams = FilterTeams(teamsList)
        val filterList = filterTeams.filterTeams(events = FilterTypes.SortByAlpahbets)
        Assert.assertNotEquals(teamsList[0].fullName,filterList[0].fullName)
    }

    private fun setUpFilterData(){
        val team1 = Team(fullName = "Chicago Bulls",wins = 16,losses = 5,id = 2,players = arrayListOf())
        val team2 = Team(fullName = "Boston Celtics",wins = 15,losses = 8,id = 1,players = arrayListOf())
        val team3 = Team(fullName = "Detroit Pistons",wins = 20,losses = 6,id = 3,players = arrayListOf())

        teamsList.add(team1)
        teamsList.add(team2)
        teamsList.add(team3)
    }
}