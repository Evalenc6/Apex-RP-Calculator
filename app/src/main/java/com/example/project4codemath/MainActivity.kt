package com.example.project4codemath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placementSlider = findViewById<SeekBar>(R.id.placementSlider)
        val placementText = findViewById<TextView>(R.id.placementText)
        val newRPGainedText = findViewById<TextView>(R.id.newRPGainedText)
        val totalRPGainedText = findViewById<TextView>(R.id.totalRPGainedText)
        val currentRankName = findViewById<Spinner>(R.id.rankNameDropDown)
        val currentRankNumber = findViewById<Spinner>(R.id.rankNumberDropDown)
        val instanceOfElims = findViewById<EditText>(R.id.elimsInput)
        val instanceOfAssists = findViewById<EditText>(R.id.assistInput)
        val instanceOfKP = findViewById<EditText>(R.id.teamKpInput)
        val submitButton = findViewById<Button>(R.id.submitScores)
        var currentRank = currentRankName.selectedItem.toString()

        var currentRankNum = currentRankNumber.selectedItem.toString()
        var currentElims = instanceOfElims.text.toString().toIntOrNull() ?: 0
        var currentAssist = instanceOfAssists.text.toString().toIntOrNull() ?: 0
        var currentTeamKP = instanceOfKP.text.toString().toIntOrNull() ?: 0
        var placement = 0
        var newRPGained = 0
        var totalRPGained = 0

        placementSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                placementText.text = progress.toString()
                placement = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })

        currentRankName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentRank = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }


        }
        currentRankNumber.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentRankNum = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

        submitButton.setOnClickListener{
            System.out.println("Button Clicked")
            val currentElims = instanceOfElims.text.toString().toInt()
            val currentAssists = instanceOfAssists.text.toString().toInt()
            val currentTeamKP = instanceOfKP.text.toString().toInt()
            val placement = placementSlider.progress.toInt()
            val rankNum = currentRankNum.toInt()
            newRPGained = calculateNewRP(currentRank, rankNum,currentElims, currentAssists, currentTeamKP, placement)
            newRPGainedText.text = newRPGained.toString()
            totalRPGained = totalRPGained+newRPGained
            totalRPGainedText.text = (totalRPGained).toString()
        }

    }
    fun calculateNewRP(rankName: String, rankNum: Int,elims: Int, assists: Int, TeamKP: Int, placement:Int) : Int{
        return -checkRankName(rankName)-checkRankNum(rankNum)+checkTheRest(elims, assists, TeamKP, placement);
    }
    fun checkRankName(name: String) : Int{
        when(name){
            "Bronze" -> return 10
            "Silver" -> return 22
            "Gold" -> return 34
            "Platinum" -> return 46
            "Diamond" -> return 58
            "Masters" -> return 70
            "Apex Predator" -> return 75
            else -> {
                return 0
            }
        }
    }
    fun checkRankNum(rankNum: Int): Int{
        when(rankNum){
            4 -> return 0
            3 -> return 3
            2 -> return 6
            1 -> return 9
            else ->{
                return -1
            }
        }
    }
    fun checkTheRest(elims: Int,assists:Int, teamKP: Int, placement:Int): Int{
        val totalKP = elims+assists+teamKP
        if(placement>15){
            return totalKP
        }else if(placement in 11..13){
            return (5+5*(totalKP))
        }else if(placement in 9..10) {
            return (10 + 10 * (totalKP))
        }else if(placement in 7..8){
            return (20+12*(totalKP))
        }else if(placement==6){
            return (30+14*(totalKP))
        }else if(placement==5){
            return (45+16*(totalKP))
        }else if(placement==4){
            return (55+ 18*(totalKP))
        }else if(placement==3){
            return (70+20*(totalKP))
        }else if(placement==2){
            return (95+20*(totalKP))
        }else if(placement==1){
            return (125+25*(totalKP))
        }else{
            return 0
        }
    }
}