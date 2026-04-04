package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator

fun <T> Set<InputValidator<T>>.failedValidatorOrNull(input: T): InputValidator<T>? =
	firstOrNull { !it.validate(input) }

fun <T> Set<InputValidator<T>>.isValid(input: T): Boolean =
	this.failedValidatorOrNull(input) == null