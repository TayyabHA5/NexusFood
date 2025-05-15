package com.tayyabbsse51f22s087re.nexusfood

import android.content.Context

object LoadingUtils {
    private var loader: LoaderClass? = null

    fun show(context: Context?, cancelable: Boolean = false) {
        hide() // Hide any existing loader first

        context?.let {
            try {
                loader = LoaderClass(it).apply {
                    setCancelable(cancelable)
                    setCanceledOnTouchOutside(cancelable)
                    show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun hide() {
        loader?.let {
            if (it.isShowing) {
                try {
                    it.dismiss()
                } catch (e: Exception) {
                    // Handle case where dialog is already dismissed
                    e.printStackTrace()
                } finally {
                    loader = null
                }
            }
        }
    }
}