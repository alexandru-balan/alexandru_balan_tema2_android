package alexandru.balan.tema2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {
    @Query("Select * from students")
    fun getAll() : LiveData<List<Student>>

    @Query("Select * from students where name = :name")
    suspend fun getByName(name: String): List<Student>

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)
}