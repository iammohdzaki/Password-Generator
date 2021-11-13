package data.response

import data.entity.Response

/**
Created by Mohammad Zaki
on Nov,13 2021
 **/
interface Callback {
    fun onPasswordGenerated(response: Response)
}