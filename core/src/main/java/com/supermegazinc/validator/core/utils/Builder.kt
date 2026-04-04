package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator

class InputValidatorBuilder<T>(
	val validate: (input: T) -> Boolean
) : InputValidator<T> {
	override fun validate(input: T): Boolean = validate.invoke(input)
}