package com.supermegazinc.validator.compose

import com.supermegazinc.flow.compose.SuperMutableStateCompose
import com.supermegazinc.validator.core.InputValidator

data class ValidatedInputCompose<T>(
	val inputState: SuperMutableStateCompose<T>,
	val failedValidator: InputValidator<T>?
)