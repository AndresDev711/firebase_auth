package com.andre.dev.demo.authfirebase.di.firebase

import com.andre.dev.demo.authfirebase.data.repository.firebase.AuthRepo
import com.andre.dev.demo.authfirebase.domain.repository.firebase.IAuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthPhoneModule {

  @Provides
  @Singleton
  fun provideFirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()
  }

  @Provides
  @Singleton
  fun provideAuthRepository(firebaseAuth: FirebaseAuth): IAuthRepo {
    return AuthRepo(firebaseAuth)
  }

  @Provides
  @Singleton
  fun provideAuthenticationUseCase(authRepository: AuthRepository): AuthenticationUseCase {
    return AuthenticationUseCase(authRepository)
  }

}