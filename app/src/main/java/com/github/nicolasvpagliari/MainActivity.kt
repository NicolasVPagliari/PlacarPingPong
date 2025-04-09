package com.github.nicolasvpagliari

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.nicolasvpagliari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var playerOneScore = 0
    private var playerTwoScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btFinishMatch.setOnClickListener {
            val ret = Intent()
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_ONE_NAME, binding.tvPlayerOneName.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_TWO_NAME, binding.tvPlayerTwoName.text.toString())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_ONE_SCORE,
                binding.tvPlayerOneScore.text.toString().toInt())
            ret.putExtra(KEY_RESULT_EXTRA_PLAYER_TWO_SCORE,
                binding.tvPlayerTwoScore.text.toString().toInt())
            setResult(RESULT_OK, ret)
            super.finish()
        }

        setUpExtras(savedInstanceState)

        setUpListener()
    }

    private fun setUpListener() {
        binding.btPlayerOneScore.setOnClickListener {
            playerOneScore++
            binding.tvPlayerOneScore.text = playerOneScore.toString()
        }

        binding.btPlayerTwoScore.setOnClickListener {
            playerTwoScore++
            binding.tvPlayerTwoScore.text = playerTwoScore.toString()
        }


    }

    private fun setUpExtras(savedInstanceState: Bundle?) {
        binding.tvPlayerOneName.text = intent.getStringExtra("PLAYER1")
        binding.tvPlayerTwoName.text = intent.getStringExtra("PLAYER2")

        if (savedInstanceState != null) {
            playerOneScore = savedInstanceState.getInt("PLAYER_ONE_SCORE")
            playerTwoScore = savedInstanceState.getInt("PLAYER_TWO_SCORE")
            binding.tvPlayerOneScore.text = playerOneScore.toString()
            binding.tvPlayerTwoName.text = playerTwoScore.toString()
        }
    }

    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PLAYER_ONE_SCORE", playerOneScore)
        outState.putInt("PLAYER_TWO_SCORE", playerTwoScore)
    }



}