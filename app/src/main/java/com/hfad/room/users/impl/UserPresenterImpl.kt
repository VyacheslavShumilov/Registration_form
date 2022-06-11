package com.hfad.room.users.impl

class UserPresenterImpl: UsersContract.Presenter {

    private var mvpView: UsersContract.View? = null

    override fun responseData() {
        mvpView?.let {  }
    }

    override fun attachView(view: UsersContract.View) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }
}