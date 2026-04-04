package com.supermegazinc.validator.reactive

import com.supermegazinc.flow.core.SuperMutableState
import com.supermegazinc.validator.core.InputValidator

data class ValidatedInputReactive<T>(
	val validators: SuperMutableState<Set<InputValidator<T>>>,
	val value: SuperMutableState<T>
)