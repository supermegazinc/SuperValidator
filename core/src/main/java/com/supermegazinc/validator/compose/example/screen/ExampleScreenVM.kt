package com.supermegazinc.validator.compose.example.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supermegazinc.flow.core.utils.set
import com.supermegazinc.validator.compose.utils.compose
import com.supermegazinc.validator.core.utils.StringValidatorBuilder
import com.supermegazinc.validator.reactive.utils.isValidFlow
import com.supermegazinc.validator.reactive.utils.reactive
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class ExampleScreenVM() : ViewModel() {

	private val exampleForm = ExampleForm()

	val exampleFormReactive = ExampleFormReactive(
		username = exampleForm.username.reactive(),
		password = exampleForm.password.reactive(),
		password2 = exampleForm.password2.reactive(),
	)

	val isReady = listOf(
		exampleFormReactive.username,
		exampleFormReactive.password,
		exampleFormReactive.password2
	)
		.isValidFlow()
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

	init {
		exampleFormReactive.password.value.value
			.onEach { passwordValue ->
				val matchPasswordValidator = StringValidatorBuilder { it == passwordValue }
				exampleFormReactive.password2.validators.set(exampleForm.password2.validators + matchPasswordValidator)
			}
			.launchIn(viewModelScope)
	}
}

@Composable
fun ExampleFormReactive.compose() = ExampleFormCompose(
	username = username.compose(),
	password = password.compose(),
	password2 = password2.compose(),
)