package com.supermegazinc.validator.example

import com.supermegazinc.validator.core.utils.ValidatedString
import com.supermegazinc.validator.core.utils.failedValidatorOrNull
import com.supermegazinc.validator.core.utils.isValid
import com.supermegazinc.validator.core.utils.validated
import com.supermegazinc.validator.validators.StringValidators

fun inputExample() {

	//How validation data is stored in the backend
	//You save an initial value and a set of input validators
	data class InputData(
		val name: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
		val lastName: ValidatedString = "".validated(setOf(StringValidators.EmptyField)),
	)

	val inputData = InputData()

	//Check if a name is valid
	val nameIsValid = inputData.name.validators.isValid("Lucas")
	//Check which validation was failed - EmptyField
	val failedValidator = inputData.lastName.validators.failedValidatorOrNull("name")

}