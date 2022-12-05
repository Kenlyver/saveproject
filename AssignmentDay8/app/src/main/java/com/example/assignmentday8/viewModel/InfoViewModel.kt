package com.example.assignmentday8.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmentday8.model.dbCake.CakeDatabase
import com.example.assignmentday8.model.dbInfo.Info
import com.example.assignmentday8.model.dbInfo.InfoDatabase
import com.example.assignmentday8.model.dbWallet.Wallet
import com.example.assignmentday8.model.dbWallet.WalletDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoViewModel(app: Application) : AndroidViewModel(app) {
    private val infoDao = InfoDatabase.getInstance(app.applicationContext).infoDao()
    private val cakeDao = CakeDatabase.getInstance(app.applicationContext).cakeDao()
    private val walletDao = WalletDatabase.getInstance(app.applicationContext).walletDao()

    val wallet = walletDao.getWalletLiveData()
    val cake = cakeDao.getCakeLiveData()

    private val _numberCake = MutableLiveData(0)
    val numberCake: LiveData<Int>
        get() = _numberCake

    fun insertWallet(wallet: Wallet){
        viewModelScope.launch(Dispatchers.IO) {
            walletDao.insertWallet(wallet)
        }
    }

    fun insertInfo(info: Info) {
        viewModelScope.launch(Dispatchers.IO) {
            infoDao.insertInfo(info)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            infoDao.deleteAll()
        }
    }

    fun updateInfo(info: Info) {
        viewModelScope.launch(Dispatchers.IO) {
            infoDao.updateInfo(info)
        }
    }

    fun updateAmoutCake(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            cakeDao.updateAmountCake(number)
        }
    }
    fun updateWalletUSD(wallet: String) {
        viewModelScope.launch(Dispatchers.IO) {
            walletDao.updateUSD(wallet)
        }
    }
    fun updateWalletVND(wallet: String) {
        viewModelScope.launch(Dispatchers.IO) {
            walletDao.updateVND(wallet)
        }
    }

    fun upCake() {
        _numberCake.value = numberCake.value?.plus(1)
    }

    fun downCake() {
        if (numberCake.value?.toInt()!! > 0) {
            _numberCake.value = numberCake.value?.minus(1)
        }
    }

    fun resetCake() {
        _numberCake.value = 0
    }
}