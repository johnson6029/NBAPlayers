package com.example.nbaplayers.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.DataManager
import com.example.nbaplayers.R
import com.example.nbaplayers.adepter.PlayerAdapter
import com.example.nbaplayers.models.Player
import com.example.nbaplayers.models.Team
import kotlinx.android.synthetic.main.item_player_row.*
import kotlinx.android.synthetic.main.team_page_screen.*

class PlayerActivity : AppCompatActivity() {

    private lateinit var team: Team
    private var teamId: Int = 1
    private lateinit var playerList: ArrayList<Player>
    private lateinit var playerAdapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_page_screen)
        initData()
        initUI()
    }

    private fun initData() {
        teamId = intent.getIntExtra("TeamId", 1)
        team = DataManager.getTeamsDataById(teamId)
        playerList = team.players
        playerAdapter = PlayerAdapter(playerList)
    }

    private fun initUI() {
        teamName.text = team.fullName
        teamWinsTV.text = team.wins.toString()
        teamLossesTV.text = team.losses.toString()
        playersRecylerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        playersRecylerView.adapter = playerAdapter
    }
}