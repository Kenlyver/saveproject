package com.example.serverorder.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.serverorder.adapter.Constant.AUTHORITY
import com.example.serverorder.adapter.Constant.CONTENT_PATH
import com.example.serverorder.source.DataRespository

class AppContentProvider : ContentProvider() {

    private lateinit var respository: DataRespository

    private val uriM = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, CONTENT_PATH, 1)
        addURI(AUTHORITY, CONTENT_PATH, 2)
    }

    override fun onCreate(): Boolean {
        respository = DataRespository.getInstance(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return respository.getInfor()
    }

    override fun getType(uri: Uri): String? {
        return when (uriM.match(uri)) {
            1 -> {
                "111"
            }
            2 -> {
                "aaa"
            }
            else -> throw IllegalArgumentException("Not support: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }


}