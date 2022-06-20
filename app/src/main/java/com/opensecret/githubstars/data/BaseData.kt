package com.opensecret.githubstars.data

open class BaseData {

    companion object {
        const val HEADER = 0
        const val USER = 1
    }

    private var mType: Int = -1
    var index: String = ""

    fun setType(type: Int) {
        mType = type
    }

    fun getType(): Int = mType
}