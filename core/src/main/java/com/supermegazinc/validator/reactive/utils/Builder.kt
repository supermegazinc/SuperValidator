package com.supermegazinc.validator.reactive.utils

import com.supermegazinc.flow.core.utils.asSuperMutableState
import com.supermegazinc.validator.core.InputValidators
import com.supermegazinc.validator.reactive.ValidatedInputReactive
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> InputValidators<T>.reactive(initialValue: T): ValidatedInputReactive<T> {

	val value = MutableStateFlow(initialValue).asSuperMutableState()

	val validators = MutableStateFlow(this.validators).asSuperMutableState()

	return ValidatedInputReactive(
		validators = validators,
		value = value
	)

}

fun <T>ValidatedInputReactive(validators: InputValidators<T>, initialValue: T) = validators.reactive(initialValue)