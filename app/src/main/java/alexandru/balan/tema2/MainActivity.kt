package alexandru.balan.tema2

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        val students : List<Student> = emptyList()
        val adapter = StudentAdapter(students)

        main_recycler.adapter = adapter
        main_recycler.layoutManager = LinearLayoutManager(this);

        studentViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(StudentViewModel::class.java)
        studentViewModel.allStudents.observe(this, Observer { students -> students?.let { adapter.setStudents(it) } })

        val addButton = findViewById<Button>(R.id.btn_1)
        addButton.setOnClickListener {
            // Getting the text and mark from the editTexts
            val name : String = findViewById<EditText>(R.id.edit_1).text.toString();
            val mark = edit_2.text.toString()

            val markInt : Int

            if (name.isBlank()) {
                val toast : Toast = Toast.makeText(applicationContext, "No name inserted", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
                return@setOnClickListener
            }

            if (mark.isBlank() || mark.toInt() !in 1..10) {
                val toast : Toast = Toast.makeText(applicationContext, "No mark inserted or wrong value", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
                return@setOnClickListener
            }
            else {
                markInt = mark.toInt()
            }

            val newStudent = Student(name, markInt)

            studentViewModel.insert(newStudent);
        }

        btn_2.setOnClickListener {
            val name: String = edit_1.text.toString()

            val studentsToDelete = runBlocking { return@runBlocking studentViewModel.getByName(name) }

            if (studentsToDelete.isEmpty()) {
                val toast : Toast = Toast.makeText(applicationContext, "Student not found", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }
            else
                studentViewModel.delete(studentsToDelete)
        }
    }

}