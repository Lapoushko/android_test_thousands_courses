package com.lapoushko.storage

class NativeLib {

    /**
     * A native method that is implemented by the 'storage' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'storage' library on application startup.
        init {
            System.loadLibrary("storage")
        }
    }
}