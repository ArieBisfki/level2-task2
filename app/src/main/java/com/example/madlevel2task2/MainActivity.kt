package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val questionAdapter = QuestionAdapter(Question.list)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        createItemTouchHelper().attachToRecyclerView(binding.rvQuestions)
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = Question.list[position]

                val correctDirectionToSwipeTo =
                        if (question.statementIsCorrect) ItemTouchHelper.RIGHT
                        else ItemTouchHelper.LEFT

                if (direction == correctDirectionToSwipeTo) {
                    onCorrectSwipe(question)
                } else {
                    onIncorrectSwipe(viewHolder.itemView)
                }
            }
        }
        return ItemTouchHelper(callback)
    }

    /**
     * This method gets called when the user does a swipe in the correct (according to the predefined correctness of the question) direction.
     */
    private fun onCorrectSwipe(question: Question) {
        Question.list.remove(question)
        questionAdapter.notifyDataSetChanged()
    }

    /**
     * This method gets called when the user does a swipe in the incorrect (according to the predefined correctness of the question) direction.
     */
    private fun onIncorrectSwipe(itemView: View) {
        Snackbar.make(itemView, getString(R.string.wrong), Snackbar.LENGTH_SHORT).show()
        questionAdapter.notifyDataSetChanged()
    }
}