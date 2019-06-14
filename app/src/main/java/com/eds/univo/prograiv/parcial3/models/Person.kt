package com.eds.univo.prograiv.parcial3.models


/** * Created by nhulox97 on 12,junio,2019 */
class Person {
    var id: Int? = null
    var name: String? = null
    var phoneNumber: String? = null
    var DUI: String? = null

    constructor(id: Int, name: String, phoneNumber: String, DUI: String){
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
        this.DUI = DUI
    }
}