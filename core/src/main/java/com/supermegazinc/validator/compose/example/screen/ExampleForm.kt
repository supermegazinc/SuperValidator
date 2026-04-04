package com.supermegazinc.validator.compose.example.screen

import com.supermegazinc.validator.compose.utils.ValidatedStringCompose
import com.supermegazinc.validator.core.utils.ValidatedString
import com.supermegazinc.validator.core.utils.validated
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.validators.StringValidators

data class ExampleForm(
	val username: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
	val password: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
	val password2: ValidatedString = "".validated(setOf(StringValidators.EmptyField))
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