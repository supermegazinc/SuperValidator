package com.supermegazinc.validator.reactive.utils

import com.supermegazinc.validator.reactive.ValidatedInputReactive

fun <T> ValidatedInputReactive<T>.render(): T = value.value.value