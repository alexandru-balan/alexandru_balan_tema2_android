package alexandru.balan.tema2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter() : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    private var students : List<Student> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fullNameTV : TextView = itemView.findViewById(R.id.textView_name)
        var markTV : TextView = itemView.findViewById(R.id.textView_mark)
    }

    constructor(students : List<Student>) : this() {
        this.students = students;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context : Context = parent.context;
        val inflater = LayoutInflater.from(context)

        // Inflate the layout that holds the info about students
        val studentView : View = inflater.inflate(R.layout.row_layout, parent, false);

        // Return the new ViewHolder instance
        return ViewHolder(studentView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        // Set item view based on the info from the model
        holder.fullNameTV.text = student.name;
        holder.markTV.text = student.mark.toString();
    }

    override fun getItemCount(): Int {
        return this.students.size;
    }

    internal fun setStudents (students : List<Student>) {
        this.students = students;
        notifyDataSetChanged();
    }

}