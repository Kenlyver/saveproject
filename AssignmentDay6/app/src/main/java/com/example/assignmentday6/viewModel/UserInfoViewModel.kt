package com.example.assignmentday6.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentday6.Model.User

class UserInfoViewModel : ViewModel() {
    val user = MutableLiveData<User>()

    init {
        user.value = User(
            "Kenlyver",
            "https://media.wired.com/photos/5955ceabcbd9b77a41915cf6/master/pass/marvel-characters.jpg"
        )
    }
}