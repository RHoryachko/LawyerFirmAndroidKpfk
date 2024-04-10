package com.example.lawyermanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LawyerManagerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ManagerAdapter
    private val dataSet = arrayListOf<ManagerModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_manager)

        val add=findViewById<Button>(R.id.button_add)
        val finish=findViewById<Button>(R.id.button_finish)

        finish.setOnClickListener{
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ManagerAdapter(dataSet){position->
            showUpdateDialog(position = position, managerModel = dataSet[position] )
        }
        recyclerView.adapter = adapter

        dataSet.addAll(arrayListOf(
            ManagerModel("Case 1", "Smith James", 378 ),
            ManagerModel("Case 2", "Williams Charles", 925 ),
            ManagerModel("Case 3", "Garcia Thomas", 156 ),
            ManagerModel("Case 4", "Johnson Joseph", 482 ),
            ManagerModel("Case 5", "Davis Robert", 223 ),
            ManagerModel("Case 6", "Martinez Richard ", 234 ),
            ManagerModel("Case 7", "Miller Michael", 134 ),
            ManagerModel("Case 8", "Rodriguez Joh", 123 ),
            ManagerModel("Case 9", "Brown David ", 131 ),
            ManagerModel("Case 10", "Jones William", 345 ),


        ))
        add.setOnClickListener(){
            showAddTeacherDialog()
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAddTeacherDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_manager, null)
        val sEditText = dialogView.findViewById<EditText>(R.id.surnameEditText)
        val nEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val pEditText = dialogView.findViewById<EditText>(R.id.patronimicEditText)

        builder.setView(dialogView)
            .setTitle("Add case")
            .setPositiveButton("Add") { dialog, which ->
                // Отримуємо дані з полів вводу та додаємо новий елемент у список
                val whenMonth = sEditText.text.toString()
                val name = nEditText.text.toString()
                val howMany = pEditText.text.toString().toInt()


                dataSet.add(ManagerModel(name, whenMonth, howMany))
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            .show()
    }

    private fun showUpdateDialog(position: Int, managerModel: ManagerModel) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_manager, null)

        val sEditText = dialogView.findViewById<EditText>(R.id.surnameEditText)
        val nEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val pEditText = dialogView.findViewById<EditText>(R.id.patronimicEditText)

        // Встановлюємо існуючі значення в поля для вводу
        sEditText.setText(managerModel.whenMonth)
        nEditText.setText(managerModel.name)
        pEditText.setText(managerModel.howMany.toString())


        builder.setView(dialogView)
            .setTitle("Edit")
            .setPositiveButton("Change") { dialog, which ->
                // Отримуємо оновлені дані з полів вводу
                val whenMonth = sEditText.text.toString()
                val name = nEditText.text.toString()
                val howMany = pEditText.text.toString().toInt()

                // Оновлюємо елемент у списку
                val updatedTeacher = ManagerModel(name, whenMonth, howMany)
                dataSet[position] = updatedTeacher
                adapter.notifyItemChanged(position)
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Відміна оновлення
                dialog.cancel()
            }
            .show()
    }

}