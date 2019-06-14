package com.eds.univo.prograiv.parcial3.models


/** * Created by nhulox97 on 12,junio,2019 */
class Expenditure {
    var id: Int? = null
    var name: String? = null
    var amount: String? = null
    var price: String? = null

    constructor(id: Int, name: String, amount: String, price: String) {
        this.id = id
        this.name = name
        this.amount = amount
        this.price = price
    }
}