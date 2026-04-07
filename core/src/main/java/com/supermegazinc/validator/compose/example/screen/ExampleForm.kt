package com.supermegazinc.validator.compose.example.screen

import com.supermegazinc.validator.compose.utils.ValidatedStringCompose
import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.validators.CommonStringValidators

data class ExampleForm(
	val username: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
	val password: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
	val password2: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
)

data class ExampleFormReactive(
	val username: ValidatedStringReactive,
	val password: ValidatedStringReactive,
	val password2: ValidatedStringReactive,
)

data class ExampleFormCompose(
	val username: ValidatedStringCompose,
	val password: ValidatedStringCompose,
	val password2: ValidatedStringCompose,
)