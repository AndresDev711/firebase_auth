package com.andre.dev.demo.authfirebase.data.repository.firebase

import com.andre.dev.demo.authfirebase.core.utils.Resource
import com.andre.dev.demo.authfirebase.domain.repository.firebase.IAuthRepo
import com.andre.dev.demo.authfirebase.ui.views.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepo @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : IAuthRepo {

  private val coroutineScope = CoroutineScope(Dispatchers.IO)

  override fun phoneNumberSignIn(
    phoneNumber: String,
    activity: MainActivity
  ): Flow<Resource<Boolean>> = channelFlow {
    try {
      trySend(Resource.Loading).isSuccess
      val options = PhoneAuthOptions.newBuilder(firebaseAuth)
        .setPhoneNumber(phoneNumber)
        .setActivity(activity)
        .setTimeout(60, TimeUnit.SECONDS)
        .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
          override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            coroutineScope.launch {
              signInWithAuthCredential(p0)
            }
          }

          override fun onVerificationFailed(p0: FirebaseException) {
            coroutineScope.launch {
              trySend(
                Resource.Error(
                  p0.localizedMessage ?: "An Error Occurred"
                )
              ).isSuccess
            }
          }

          override fun onCodeSent(
            verificationId: String,
            p1: PhoneAuthProvider.ForceResendingToken,
          ) {
            coroutineScope.launch {
              withContext(Dispatchers.Main) {
              }
              activity.otpValue.collect {
                if (it.isNotBlank()) {
                  trySend(
                    signInWithAuthCredential(
                      PhoneAuthProvider.getCredential(
                        verificationId,
                        it
                      )
                    )
                  ).isSuccess
                }
              }
            }
          }

        }).build()
      PhoneAuthProvider.verifyPhoneNumber(options)
      awaitClose()
    } catch (exception: Exception) {
      Resource.Error(exception.localizedMessage ?: "An Error Occurred")
    }
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