package com.example.testthread

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.testthread.databinding.ActivityMainBinding
import com.example.testthread.dp.User
import com.example.testthread.dp.UserDatabase
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: UserDatabase
    private val job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "database-name")
            .build()

        val user1 = User("Sang", "Bach", 24, true)
        val user2 = User("Kenly", "Ver", 25, true)
        val user3 = User("Huy", "Nguyen", 20, false)
        /* Sử dụng Coroutine thông qua GlobalScope để xóa các dữ liệu trước đó
        và thêm các dữ liệu mới vào
        * */
        GlobalScope.launch {
            db.userDao().deleteAll()
            db.userDao().insert(user1, user2, user3)
        }

        /*Khi Click vào nút Rool, thì Coroutine sẽ hoạt động ở luồng Worker, tại đây sẽ dùng hàm
        * random để random 1 số từ 0 đến 10 và hiển thị thông tin user theo điều kiện lên màn hình */
        binding.btnRoll.setOnClickListener {
            GlobalScope.launch(Dispatchers.Default){
                val randomnumber = (0..10).random()
                displayUsers(randomnumber)
            }
        }

        binding.btnStart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO + job) {
                /*tại đây sử dụng lifecycleScope để dùng Coroutine, dùng Dispatchers để quản lý
                các Thread mà Coroutine này chạy, tại đây dùng Dispatchrer.IO để chạy Coroutine và xử lý trên luồng
                Worker-luồng này để xử lý các tác vụ nặng. Tại đây ta cũng dùng Job để quản lý các task của Coroutine
                giúp dễ dàng control các task đó
                * */
                var j = 0
                for (i in 1..500) {
                    delay(1000)
                    if (i % 9 == 0) {
                        j = i + 1000
                    }
                    /* Sau khi sử lý các tác vụ tính toán xong, ta chuyển luồng sử dụng Coroutine bằng cách dùng withContext
                    để chuyển luồng xử lý sang luồng Main, luồng này sẽ cho phép ta vẽ View lên ứng dụng, nếu sử dụng luồng Worker
                    sẽ gây ra crash ứng dụng
                    * */
                    withContext(Dispatchers.Main) {
                        binding.txtText1.text = "$j"
                        binding.txtText2.text = "$i"
                    }
                }
            }

        }
        binding.btnCancel.setOnClickListener {
            /* Để thoát luồng Coroutine đang chạy ta sử dụng cancel() của Job, điều này sẽ ngừng tất cả các task
            của Job và ta k thể tái sử dụng Job này
            * */
            job.cancel()
        }
        binding.btnCancelChildren.setOnClickListener {
            /* Ngoài cancel ta có thể dùng cancelChildren(), nhưng cancelChildren() chỉ dừng các task của Job và
            ta có thể tái sử dụng lại Job này
            * */
            job.cancelChildren()
        }

    }

    private suspend fun displayUsers(i:Int):String {
        val users: List<User> = db.userDao().getAllUsers()
        var displayText = ""
        if (i % 3 == 0) displayText =
                "${users.get(0).name} ${users.get(0).lastname}:${users.get(0).age} year old"
        else if (i % 5 == 0) displayText =
                "${users.get(1).name} ${users.get(1).lastname}:${users.get(1).age} year old"
        else displayText =
            "${users.get(2).name} ${users.get(2).lastname}:${users.get(2).age} year old"
            binding.txtUser.text = displayText
        return displayText
    }
}
