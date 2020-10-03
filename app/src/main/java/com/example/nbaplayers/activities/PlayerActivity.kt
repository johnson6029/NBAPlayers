package com.example.nbaplayers.activities

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaplayers.LocalDataStorage
import com.example.nbaplayers.R
import com.example.nbaplayers.adepter.PlayerAdapter
import com.example.nbaplayers.models.Player
import com.example.nbaplayers.models.Team
import kotlinx.android.synthetic.main.team_page_screen.*


class PlayerActivity : AppCompatActivity() {
    // properties
    private lateinit var team: Team
    private var teamId: Int = 1
    private lateinit var playerList: ArrayList<Player>
    private lateinit var playerAdapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_page_screen)
        setUpActionBar()
        initData()
        initUI()
    }

    // helpers
    private fun setUpActionBar() {
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val textView = TextView(this)
        textView.text = getString(R.string.app_name)
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        textView.gravity = Gravity.CENTER
        textView.setTextColor(resources.getColor(android.R.color.white))
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.customView = textView
    }

    private fun initData() {
        teamId = intent.getIntExtra("TeamId", 1)
        team = LocalDataStorage.getTeamsDataById(teamId)
        playerList = team.players
        playerAdapter = PlayerAdapter(playerList, PlayerActivity@ this);
    }

    private fun initUI() {
        teamName.text = team.fullName
        teamWinsTV.text = team.wins.toString()
        teamLossesTV.text = team.losses.toString()
        playersRecylerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        playersRecylerView.adapter = playerAdapter
    }
}