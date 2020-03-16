package alexandru.balan.tema2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
class Student() {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    var name: String = "Unknown"
    var mark: Int = -1

    constructor(name: String, mark: Int) : this() {
        this.name = name

        if (mark in 1..10) {
            this.mark = mark
        }
    }


}