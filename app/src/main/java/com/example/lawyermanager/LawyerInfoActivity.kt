package com.example.lawyermanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LawyerInfoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LawyerAdapter
    private val dataSet = arrayListOf<LawyerModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_info)

        val finish=findViewById<Button>(R.id.button_finish)

        finish.setOnClickListener{
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = LawyerAdapter(dataSet){ position->
            showUpdateDialog(position = position, lawyerModel = dataSet[position] )
        }
        recyclerView.adapter = adapter

        dataSet.addAll(arrayListOf(
            LawyerModel("Emily Johnson", " emily.johnson@example.com", "+1 (555) 123-4567", " 123 Main Street, Anytown"),
            LawyerModel("Ethan Smith", "ethan.smith@example.com", "+1 (555) 987-6543", "456 Elm Street, Otherville"),
            LawyerModel("Olivia Garcia", "olivia.garcia@example.com", "+1 (555) 567-8901", "ak Avenue, Anycity,"),
            LawyerModel("Liam Martinez", "liam.martinez@example.com", "+1 (555) 234-5678", "101 Pine Road, Anytown"),
            LawyerModel("Ava Brown", "ava.brown@example.com", "+1 (555) 876-5432", "Maple Lane, Otherville"),
            LawyerModel("Noah Rodriguez", "noah.rodriguez@example.com", "+1 (555) 345-6789", "333 Birch Street, Anycity"),
            LawyerModel("Isabella Wilson", "isabella.wilson@example.com", "+1 (555) 654-3210", "555 Spruce Court, Otherville"),
            LawyerModel("James Lee", "james.lee@example.com", "+1 (555) 789-0123", "54 Silent Court, Otherville"),
            LawyerModel("Mia Thompson", "mia.thompson@example.com", " +1 (555) 432-1098", "666 Oak Avenue, Anycity"),
            LawyerModel("Alexander Harris", "alexander.harris@example.com", "+1 (555) 210-9876", "12 Birch Street, Anycity")
            ))


    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")


    private fun showUpdateDialog(position: Int, lawyerModel: LawyerModel) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.lawyer_info_dialog, null)

        val start = dialogView.findViewById<TextView>(R.id.start_month)
        val end = dialogView.findViewById<TextView>(R.id.end_month)
        val time = dialogView.findViewById<TextView>(R.id.time)

        start.text="Email:"
        end.text=lawyerModel.startMonth+". Phone: "+lawyerModel.endMonth
        time.text="Adress: "+lawyerModel.timeToGrow

        builder.setView(dialogView)
            .setTitle(lawyerModel.name)
            .setNegativeButton("Back") { dialog, which ->
                dialog.cancel()
            }
            .show()
    }

}
