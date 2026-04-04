package com.supermegazinc.validator.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.supermegazinc.flow.compose.utils.compose
import com.supermegazinc.validator.compose.ValidatedInputCompose
import com.supermegazinc.validator.reactive.ValidatedInputReactive
import com.supermegazinc.validator.reactive.utils.failedValidatorFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop

@Composable
fun <T> ValidatedInputReactive<T>.compose(): ValidatedInputCompose<T> {

	val error by remember(this) {
		this.value.value
			.drop(1)
			.combine(this.failedValidatorFlow()) { _, e -> e }
	}.collectAsStateWithLifecycle(null)

	val inputState = this.value.compose()
	return remember(this, error) {
		ValidatedInputCompose(
			inputState = inputState,
			failedValidator = error
		)
	}

}