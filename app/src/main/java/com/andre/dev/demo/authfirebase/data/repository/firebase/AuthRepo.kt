package com.andre.dev.demo.authfirebase.data.repository.firebase

import com.andre.dev.demo.authfirebase.domain.repository.firebase.IAuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepo @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : IAuthRepo {
  override fun phoneNumberSignIn(
    phoneNumber: String,
    activity: MainActivity
  ): Flow<Resource<Boolean>> {
    TODO("Not yet implemented")
  }

  override fun isUserAuthenticated(): Boolean {
    TODO("Not yet implemented")
  }

  override fun getUserId(): String {
    TODO("Not yet implemented")
  }

  override suspend fun signInWithAuthCredential(phoneAuthCredential: PhoneAuthCredential): Resource<Boolean> {
    TODO("Not yet implemented")
  }
}