package org.android.go.sopt.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.util.AssetLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AssetLoaderModule {
    @Provides
    @Singleton
    fun providesAssetLoader(@ApplicationContext context: Context): AssetLoader =
        AssetLoader(context)
}
