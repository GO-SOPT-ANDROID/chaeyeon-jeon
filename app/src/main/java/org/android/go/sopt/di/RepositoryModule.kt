package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.repository.AuthRepositoryImpl
import org.android.go.sopt.data.repository.FollowerRepositoryImpl
import org.android.go.sopt.data.repository.RepoRepositoryImpl
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.domain.repository.FollowerRepository
import org.android.go.sopt.domain.repository.RepoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsRepoRepository(
        repoRepositoryImpl: RepoRepositoryImpl,
    ): RepoRepository

    @Binds
    @Singleton
    abstract fun bindsFollowerRepository(
        followerRepositoryImpl: FollowerRepositoryImpl,
    ): FollowerRepository
}
