package id.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.advweek4.R
import id.ubaya.advweek4.databinding.StudentListItemBinding
import id.ubaya.advweek4.model.Student
import id.ubaya.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList: ArrayList<Student>) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), StudentDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = StudentListItemBinding.inflate(inflater, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.view) {
            student = studentList[position]
            detailListener = this@StudentListAdapter
        }
//        val student = studentList[position]
//        var studentId = student.id.toString()
//        with (holder.view) {
//            textID.text = student.id
//            textName.text = student.name
//            buttonDetail.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(studentId)
//                Navigation.findNavController(it).navigate(action)
//            }
//            imageStudentPhoto.loadImage(student.photoURL, progressLoadingStudentPhoto)
//        }
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onStudentDetailClick(v: View) {
        var studentId = v.tag.toString()
        val action = StudentListFragmentDirections.actionStudentDetail(studentId)
        Navigation.findNavController(v).navigate(action)
    }
}