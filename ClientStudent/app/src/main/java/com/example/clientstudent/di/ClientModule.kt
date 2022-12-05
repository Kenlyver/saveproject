package com.example.clientstudent.di

import android.content.Context
import com.example.clientstudent.view.adpter.StudentAdapter
import com.example.serverstudent.controller.StudentServiceConnector
import com.example.serverstudent.controller.StudentServiceController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {
    @Provides
    @Singleton
    fun provideStudentServiceController(@ApplicationContext context: Context):StudentServiceController =
        StudentServiceController(context)

    @Provides
    @Singleton
    fun provideStudentAdapter():StudentAdapter = StudentAdapter()
}