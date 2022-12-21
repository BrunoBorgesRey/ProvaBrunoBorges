package com.example.provabrunoborges.data

import com.example.provabrunoborges.model.Vehicle
import kotlin.random.Random

object DAOSingleton {
    private var serial: Long = 1
    private val vehicles = ArrayList<Vehicle>()


    fun add(t: Vehicle) {
        this.vehicles.add(0, t)
    }
    fun getAll(): ArrayList<Vehicle> {
        return this.vehicles
    }

    fun getVehicleById(id: Long): Vehicle? {
        for(t in this.vehicles) {
            if(id == t.id)
                return t
        }
        return null
    }
    fun getVehiclePosition(vehicle: Vehicle): Int {
        return this.vehicles.indexOf(vehicle)
    }
}