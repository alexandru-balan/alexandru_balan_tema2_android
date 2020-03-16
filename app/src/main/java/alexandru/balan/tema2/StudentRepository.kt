package alexandru.balan.tema2

import androidx.lifecycle.LiveData

class StudentRepository (private val studentDAO: StudentDAO) {
    val allStudents : LiveData<List<Student>> = studentDAO.getAll();

    suspend fun insert(student: Student) {
        studentDAO.insert(student)
    }

    suspend fun getByName(name : String) : List<Student> {
        return studentDAO.getByName(name)
    }

    suspend fun delete(student: Student) {
        studentDAO.delete(student)
    }
}