package com.supermegazinc.validator.reactive.utils

import com.supermegazinc.flow.core.utils.asSuperMutableState
import com.supermegazinc.validator.core.ValidatedInput
import com.supermegazinc.validator.reactive.ValidatedInputReactive
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> ValidatedInput<T>.reactive(): ValidatedInputReactive<T> {

	val value = MutableStateFlow(initialValue).asSuperMutableState()

	val validators = MutableStateFlow(this.validators).asSuperMutableState()

	return ValidatedInputReactive(
		validators = validators,
		value = value
	)

}