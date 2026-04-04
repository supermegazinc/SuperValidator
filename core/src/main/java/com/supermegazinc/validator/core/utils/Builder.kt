package com.supermegazinc.validator.core.utils

import com.supermegazinc.validator.core.InputValidator

class StringValidatorBuilder(
	val validate: (input: String) -> Boolean
) : InputValidator<String> {
	override fun validate(input: String): Boolean = validate.invoke(input)
}