package com.example.clientorder.adapter

import android.net.Uri

object Constant {
    const val AUTHORITY = "com.example.serverorder.provider.AppContentProvider"
    const val CONTENT_PATH = "order"
    const val URL = "content://$AUTHORITY/$CONTENT_PATH"
    val PARSE_URI = Uri.parse(URL)!!
};