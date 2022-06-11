package com.hfad.room.users.impl

import com.hfad.room.model.Users
import com.hfad.room.mvp.BaseContract

interface UsersContract {
    interface View: BaseContract.View{
        fun loadUsers(users:List<Users>)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun responseData()
    }
}