package com.example.cuton

class Device {
    private lateinit var devman : String
    private lateinit var devmod : String
    private lateinit var devavs : String
    private lateinit var devaid : String

    fun newDevman(devman: String) {
        this.devman = devman
    }

    fun getDevman() = devman

    fun newDevmod(devmod: String) {
        this.devmod = devmod
    }

    fun getDevmod() = devmod

    fun newDevavs(devavs: String) {
        this.devavs = devavs
    }

    fun getDevavs() = devavs

    fun newDevaid(devaid: String) {
        this.devaid = devaid
    }

    fun getDevaid() = devaid
}