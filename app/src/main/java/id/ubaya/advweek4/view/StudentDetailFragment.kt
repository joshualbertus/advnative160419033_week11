package id.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ubaya.advweek4.R
import id.ubaya.advweek4.util.loadImage
import id.ubaya.advweek4.viewmodel.DetailViewModel
import id.ubaya.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if (arguments != null) {
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel.fetch(studentId)
        }

        observeViewModel()
    }

   private fun observeViewModel() {
       viewModel.studentLiveData.observe(viewLifecycleOwner) {
           val student = it
           student?.let {
               editID.setText(it.id)
               editName.setText(it.name)
               editDOB.setText(it.dob)
               editPhone.setText(it.phone)
               imageDetailStudent.loadImage(it.photoURL, progressBarLoadingPhoto)
               buttonNotif.setOnClickListener{
                   Observable.timer(5, TimeUnit.SECONDS)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe {
                           Log.d("mynotif", "Notification delayed after 5 seconds")
                           MainActivity.showNotification(student.name.toString(), "Notification created", R.drawable.ic_baseline_person_24)
                       }
               }
           }
       }
   }
}