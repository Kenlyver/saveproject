// ServerAIDL.aidl
package com.example.serverstudentapp;
import com.example.serverstudentapp.model.Student;
// Declare any non-default types here with import statements

interface ServerAIDL {
    void sendAction(String Action);
    void insertStudent(in Student s);
    void updateStudent(in Student s);
    void deleteStudent(in Student s);
    List<Student> findStudent(in String name);
    List<Student> sortStudent(in int type);
}