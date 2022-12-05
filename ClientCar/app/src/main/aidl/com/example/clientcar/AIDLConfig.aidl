// AIDLConfig.aidl
package com.example.clientcar;
import com.example.clientcar.model.DataTPMS;
// Declare any non-default types here with import statements

interface AIDLConfig {
    void sendAction(String Action);
    void insertData(in DataTPMS data);
    void updateData(in DataTPMS data);
    void deleteData(in DataTPMS data);
}