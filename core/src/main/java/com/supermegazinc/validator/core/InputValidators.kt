package com.supermegazinc.validator.core

data class InputValidators<T>(
	val validators: Set<InputValidator<T>>
)