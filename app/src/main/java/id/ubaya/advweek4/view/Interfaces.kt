package id.ubaya.advweek4.view

import android.view.View
import id.ubaya.advweek4.model.Student

interface StudentDetailClickListener {
    fun onStudentDetailClick(v: View)
}

interface StudentUpdateClickListener {
    fun onUpdateClick(v: View, obj: Student)
}

interface StudentNotificationClickListener {
    fun onNotificationClick(v: View)
}