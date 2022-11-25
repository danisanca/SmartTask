package com.example.smarttasks.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    sealed class  AuthenticationState{
        object UnauAuthenticated : AuthenticationState()
        object Authenticated : AuthenticationState()
    }
    var username:String = ""

    private val _authenticationStateEvent = MutableLiveData<AuthenticationState>()
    val authenticationStateEvent: LiveData<AuthenticationState>
        get() = _authenticationStateEvent

    fun validationauthentication(){
        if(username == ""){
            _authenticationStateEvent.value = AuthenticationState.UnauAuthenticated
        }else{
            _authenticationStateEvent.value = AuthenticationState.Authenticated
        }
    }
    fun setAuthentication(username:String){
        if (username != ""){
            this.username = username
            validationauthentication()
        }
    }

}