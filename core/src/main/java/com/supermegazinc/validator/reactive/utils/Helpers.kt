package com.supermegazinc.validator.reactive.utils

import com.supermegazinc.validator.core.InputValidator
import com.supermegazinc.validator.core.utils.failedValidatorOrNull
import com.supermegazinc.validator.reactive.ValidatedInputReactive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun <T> ValidatedInputReactive<T>.failedValidatorFlow(): Flow<InputValidator<T>?> {
	return combine(this.validators.value, this.value.value) { validators, value ->
		validators.failedValidatorOrNull(value)
	}
}

fun <T> ValidatedInputReactive<T>.failedValidatorOrNull(): InputValidator<T>? =
	validators.value.value.failedValidatorOrNull(render())

fun <T> List<ValidatedInputReactive<T>>.isValidFlow(): Flow<Boolean> {
	return this.map {
		it.failedValidatorFlow()
	}.let { flowList ->
		combine(flowList) { itemList ->
			itemList
				.filterNotNull()
				.isEmpty()
		}
	}
}