package org.newsReader.com.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


fun openLink(context: Context, link: Uri){
    val intent = Intent(Intent.ACTION_VIEW, link)
    context.startActivity(intent)
}