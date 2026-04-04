package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator
import com.supermegazinc.validator.core.ValidatedInput

fun String.validated(validators: Set<InputValidator<String>> = emptySet()): ValidatedInput<String> {
	return ValidatedInput(this, validators)
}

fun Int.validated(validators: Set<InputValidator<Int>> = emptySet()): ValidatedInput<Int> {
	return ValidatedInput(0, validators)
}
