// IStudentServiceCallback.aidl
package com.example.serverstudent.entity;

import com.example.serverstudent.entity.StudentResponse;
import com.example.serverstudent.entity.FailureResponse;
// Declare any non-default types here with import statements

interface IStudentServiceCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onGetAllStudentResponse(in StudentResponse response);

    void onInsertStudentResponse(in StudentResponse response);

    void onUpdateStudentResponse(in StudentResponse response);

    void onDeleteStudentResponse(in StudentResponse response);

    void onFailureResponse(in FailureResponse failureResponse);
}