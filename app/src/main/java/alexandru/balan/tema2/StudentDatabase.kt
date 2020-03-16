package alexandru.balan.tema2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao() : StudentDAO

    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase (context: Context) : StudentDatabase {
            val instance = INSTANCE

            if(instance != null) {
                return instance;
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "students.db").build()

                INSTANCE = instance;
                return instance;
            }
        }
    }
}