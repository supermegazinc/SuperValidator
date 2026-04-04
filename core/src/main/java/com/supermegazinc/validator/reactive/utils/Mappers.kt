package com.supermegazinc.validator.reactive.utils

import com.supermegazinc.validator.core.utils.isValid
import com.supermegazinc.validator.reactive.ValidatedInputReactive

fun <T> ValidatedInputReactive<T>.render(): T = value.value.value
fun <T> ValidatedInputReactive<T>.renderIfValid(): T? = render().takeIf {render-> validators.value.value.isValid(render) }