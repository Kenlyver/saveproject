// IStudentService.aidl
package com.example.serverstudent.entity;

import com.example.serverstudent.entity.Student;
import com.example.serverstudent.entity.IStudentServiceCallback;
// Declare any non-default types here with import statements

interface IStudentService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void registerCallback(IStudentServiceCallback callback);

    void unregisterCallback(IStudentServiceCallback callback);

    void getAllStudentRequest();

    void insertStudentRequest(in Student student);

    void updateStudentRequest(in Student student);

    void deleteStudentRequest(in Student student);
}