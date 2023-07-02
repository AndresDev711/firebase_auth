package com.andre.dev.demo.authfirebase.domain.repository.firebase

import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow

interface IAuthRepo {

  fun phoneNumberSignIn(phoneNumber : String,activity : MainActivity) : Flow<Resource<Boolean>>

  fun isUserAuthenticated() : Boolean

  fun getUserId() : String

  suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential) : Resource<Boolean>

}