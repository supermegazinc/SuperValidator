package com.supermegazinc.validator.example

import com.supermegazinc.flow.core.utils.set
import com.supermegazinc.validator.core.utils.StringValidatorBuilder
import com.supermegazinc.validator.core.utils.ValidatedString
import com.supermegazinc.validator.core.utils.validated
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.reactive.utils.failedValidatorFlow
import com.supermegazinc.validator.reactive.utils.failedValidatorOrNull
import com.supermegazinc.validator.reactive.utils.reactive
import com.supermegazinc.validator.reactive.utils.render
import com.supermegazinc.validator.validators.StringValidators
import kotlinx.coroutines.flow.onEach

fun reactiveExample() {

	//How validation data is stored in the backend
	data class InputData(
		val name: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
		val lastName: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
	)

	val inputData = InputData()

	//Define the same entity as InputData converting the ValidatedString to ValidatedStringReactive
	data class InputDataReactive(
		val name: ValidatedStringReactive,
		val lastName: ValidatedStringReactive
	)

	//Build the reactive InputData by simply converting the parameters using reactive()
	val inputDataReactive = InputDataReactive(
		name = inputData.name.reactive(),
		lastName = inputData.lastName.reactive()
	)

	//Now you can edit the field value
	inputDataReactive.name.value.set("Luca")
	//Or update it
	inputDataReactive.name.value.update { last -> last + "s" }

	//You can validate the input
	val failedValidator = inputDataReactive.name.failedValidatorOrNull().let { require(it == null) }

	//Subscribe to real-time validation updates
	val failedValidatorFlow = inputDataReactive.name.failedValidatorFlow()
		.onEach {
			val failedValidator = it
		}

	val customValidator = StringValidatorBuilder { it == "Lucas" }
	//Update the validators on runtime
	inputDataReactive.name.validators.update { it + customValidator }

	//And get the current value of the field
	val finalValue = inputDataReactive.name.render()
}