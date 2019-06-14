package com.eds.univo.prograiv.parcial3.models


/** * Created by nhulox97 on 12,junio,2019 */
class Employee {
    var id: Int? = null
    var name: String? = null
    var job: String? = null
    var DUI: String? = null

    constructor(id: Int, name: String, job: String, DUI: String){
        this.id = id
        this.name = name
        this.job = job
        this.DUI = DUI
    }
}