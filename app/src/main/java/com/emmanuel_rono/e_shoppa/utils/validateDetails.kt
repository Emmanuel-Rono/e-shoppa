package com.emmanuel_rono.e_shoppa.utils

import com.emmanuel_rono.e_shoppa.Data.Login.valid_Confirmation

fun validateDetails(username:String, password:String): valid_Confirmation
{
    if(username .isBlank() && password.isBlank()) return valid_Confirmation(false,"Username and Password cannot be Blank")
    if (username.isBlank()) return valid_Confirmation(false,"Username cannot be blank")
    if (password.isBlank()) return valid_Confirmation(false,"Password cannot be blank")
    return valid_Confirmation(true)
}
