package com.example.mad_23012531040_pratcal_7
import org.json.JSONObject
import java.io.Serializable

class Person(
    var id: String,
    var name: String,
    var email: String,
    var phone: String,
    var address: String
) : Serializable {

    constructor(jsonObject: JSONObject) : this("", "", "", "", "") {
        id = jsonObject.optString("id")
        val profileJson = jsonObject.optJSONObject("profile")
        if (profileJson != null) {
            name = profileJson.optString("name")
            email = profileJson.optString("email")
            phone = profileJson.optString("phone")
            address = profileJson.optString("address")
        }
    }

    override fun toString(): String {
        return "$name\n$email\n$phone\n$address"
    }
}