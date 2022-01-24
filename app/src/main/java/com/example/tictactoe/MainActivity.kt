package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.tictactoe.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0
     lateinit  var binding  : ActivityMainBinding
     var boardStatus = Array(3){IntArray(3)}
     lateinit var board : Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        board = arrayOf(
            arrayOf(binding.button,binding.button2,binding.button3),
            arrayOf(binding.button4,binding.button5,binding.button6),
            arrayOf(binding.button7, binding.button8, binding.button9)
        )

        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }

        initaliseBoard()

        binding.resetBtn.setOnClickListener {
            PLAYER = true
            TURN_COUNT = 0
            initaliseBoard()
        }


    }

    private fun initaliseBoard() {

        for(i in 0..2)
        {
            for(j in 0..2)
            {
                board[i][j].text = ""
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
            }
        }
    }

    override fun onClick(view : View) {
       when(view.id)
       {
           R.id.button ->{
                updateValue(0,0,PLAYER)
           }
           R.id.button2 ->{
               updateValue(0,1,PLAYER)
           }
           R.id.button3 ->{
               updateValue(0,2,PLAYER)
           }
           R.id.button4 ->{
               updateValue(1,0,PLAYER)
           }
           R.id.button5 ->{
               updateValue(1,1,PLAYER)
           }
           R.id.button6 ->{
               updateValue(1,2,PLAYER)
           }
           R.id.button7 ->{
               updateValue(2,0,PLAYER)
           }
           R.id.button8 ->{
               updateValue(2,1,PLAYER)
           }
           R.id.button9 ->{
               updateValue(2,2,PLAYER)
           }
       }

        PLAYER = !PLAYER
        TURN_COUNT += 1
        if(PLAYER)
        {
            updateDisplay("PLAYER X TURN")
        }
        else
        {
            updateDisplay("PLAYER O TURN")
        }
        if(TURN_COUNT == 9)
        {
            updateDisplay("GAME DRAW")
        }

        checkWinner()
    }

    private fun checkWinner() {

        for(i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2])
            {
                if(boardStatus[i][0] == 1)
                {
                    updateDisplay("WINNER X")
                    break;
                }
                else if(boardStatus[i][0] == 0)
                {
                    updateDisplay("WINNER O")
                    break;
                }
            }

            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i])
            {
                if(boardStatus[0][i] == 1)
                {
                    updateDisplay("WINNER X")
                    break;
                }
                else if(boardStatus[0][i] == 0)
                {
                    updateDisplay("WINNER O")
                    break;
                }
            }

        }

        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2])
        {
            if(boardStatus[0][0] == 1)
            {
                updateDisplay("WINNER X")

            }
            else if(boardStatus[0][0] == 0)
            {
                updateDisplay("WINNER O")

            }
        }

        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0])
        {
            if(boardStatus[1][1] == 1)
            {
                updateDisplay("WINNER X")

            }
            else if(boardStatus[1][1] == 0)
            {
                updateDisplay("WINNER O")

            }
        }
    }

    private fun updateDisplay(s: String) {
            val textView = findViewById<TextView>(R.id.textView)
        textView.text = s
        if(s.contains("WINNER"))
        {
            disableButton()
        }
    }

    private fun disableButton() {

        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col : Int, player: Boolean) {

        val textString =  if(player) "X" else "O"
        val value = if(player) 1 else 0

        board[row][col].apply{
            isEnabled = false
            setText(textString)
        }

        boardStatus[row][col] = value
    }
}