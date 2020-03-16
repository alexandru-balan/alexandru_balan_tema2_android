package alexandru.balan.tema2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel (application: Application) : AndroidViewModel (application) {
    private val repository : StudentRepository
    val allStudents : LiveData<List<Student>>

    init {
        val studentDAO = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDAO)
        this.allStudents = repository.allStudents
    }

    fun insert (student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    suspend fun getByName (name : String) : List<Student> {
        return repository.getByName(name)
    }

    fun delete (students : List<Student>) = viewModelScope.launch {
        for (student in students) {
            repository.delete(student)
        }
    }
}