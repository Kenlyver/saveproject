package com.example

import java.lang.reflect.Type
import java.util.*


data class HostConfig(
    val ip:String,
    val port:Int,
    val typeConnection:TypeConnection
)

enum class TypeConnection{
    WIFI,DATA,LAN;
}

fun inp():Int{
    var x=0
    val reader= Scanner(System.`in`)
    x = reader.nextInt()
    return x
}


class ManageHostConfig{
    private var listHostConfig = mutableListOf<HostConfig>()
    private lateinit var typeConnection:TypeConnection
    private fun addHostConfig(hostConfig:HostConfig){
        listHostConfig.add(hostConfig)
    }

    fun chooseConnection():TypeConnection{
        while (true){
            print("Choose type connection(1-Wifi,2-Data,3-LAN): ")
            when(readLine().toString()){
                "1" ->{
                    typeConnection = TypeConnection.WIFI
                    break
                }
                "2" ->{
                    typeConnection = TypeConnection.DATA
                    break
                }
                "3" ->{
                    typeConnection = TypeConnection.LAN
                    break
                }
                else->continue
            }
        }
        return typeConnection
    }

    fun importHostConfig(){
        var numberHost = 0
        print("Enter the number of Host: ")
        numberHost = inp()

        for(i in 0..numberHost-1){
            println("Host Config: ${(i+1)}")
            print("IP:")
            val ip = readLine().toString()
            print("Port: ")
            val port = readLine().toString().toInt()
            typeConnection = chooseConnection()

            addHostConfig(HostConfig(ip,port,typeConnection))
            addHostConfig(HostConfig(ip,port+1,typeConnection))
            addHostConfig(HostConfig(ip,port+2,typeConnection))
            addHostConfig(HostConfig(ip,port+3,typeConnection))

        }
    }

    fun getAllHost(){
        for(i in 0..listHostConfig.size-1){
            println(listHostConfig[i])
        }
    }

    fun getListOfHostConfigByIP(){
        print("Enter IP: ")
        val ip = readLine().toString()
        val listHostConfigByIP = mutableListOf<HostConfig>()
        for (i in 0..listHostConfig.size-1){
            if (listHostConfig[i].ip == ip){
                listHostConfigByIP.add(listHostConfig[i])
            }
        }
        for (i in 0..listHostConfigByIP.size-1){
            println(listHostConfigByIP[i])
        }
    }

    fun getListOfHostConfigByPort(){
        print("Enter Port: ")
        val port = readLine()?.toInt()
        val listHostConfigByPort = mutableListOf<HostConfig>()

        for (i in 0..listHostConfig.size-1){
            if (listHostConfig[i].port == port){
                listHostConfigByPort.add(listHostConfig[i])
            }
        }

        for (i in 0..listHostConfigByPort.size-1){
            println(listHostConfigByPort[i])
        }
    }

    fun getListOfHostConfigByTypeConnection(){
        val typeConnection:TypeConnection

        typeConnection = chooseConnection()

        val listHostConfigByTypeConnection = mutableListOf<HostConfig>()
        for (i in 0..listHostConfig.size-1){
            if(listHostConfig[i].typeConnection == typeConnection){
                listHostConfigByTypeConnection.add(listHostConfig[i])
            }
        }
        for (i in 0..listHostConfigByTypeConnection.size-1){
            println(listHostConfigByTypeConnection[i])
        }
    }

}


fun main(){
    val manageHostConfig = ManageHostConfig()
    manageHostConfig.importHostConfig()
        while(true){
            print("Show information all host follow (1-IP,2-Port,3-TypeConnection,0-All):")
            when(readLine().toString()){
                "1"->{
                    manageHostConfig.getListOfHostConfigByIP()
                    break
                }
                "2"->{
                    manageHostConfig.getListOfHostConfigByPort()
                    break
                }
                "3"->{
                    manageHostConfig.getListOfHostConfigByTypeConnection()
                    break
                }
                "0"->{
                    manageHostConfig.getAllHost()
                    break
                }
                else -> continue
            }
        }
}