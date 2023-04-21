package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.repository.AuthRepositoryImpl
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository
}
