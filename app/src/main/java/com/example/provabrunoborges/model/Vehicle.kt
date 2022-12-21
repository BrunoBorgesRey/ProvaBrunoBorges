package com.example.provabrunoborges.model

data class Vehicle(
    val model: String = "",
    val price: Double,
    val type: String,
    val sold: Boolean = false,
    var id: Long = 0L
){
    constructor(id: Long): this("", 0.0,"", false, 0L) {
        this.id = id
    }


}
