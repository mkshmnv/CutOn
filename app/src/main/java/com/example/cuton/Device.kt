package com.example.cuton

object Device {

    private lateinit var devman : String
    private lateinit var devmod : String
    private lateinit var devavs : String
    private lateinit var devaid : String

    fun setDevman(devman: String) {
        this.devman = devman
    }

    fun getDevman() = devman

    fun setDevmod(devmod: String) {
        this.devmod = devmod
    }

    fun getDevmod() = devmod

    fun setDevavs(devavs: String) {
        this.devavs = devavs
    }

    fun getDevavs() = devavs

    fun setDevaid(devaid: String) {
        this.devaid = devaid
    }

    fun getDevaid() = devaid
}