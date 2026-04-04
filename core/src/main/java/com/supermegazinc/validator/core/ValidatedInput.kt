package com.supermegazinc.validator.core

data class ValidatedInput<T>(
	val initialValue: T,
	val validators: Set<InputValidator<T>>
)