package com.demo.helloWorld.handlers.navigationAction

import kotlin.reflect.KClass


data class ExpandedMenuItemModel(
    val menuItemId: Int,
    val menuFragment: KClass<*>,
    val version: Int = -1,
    val isFragment: Boolean = true,
    val operation: Any? = null
)

