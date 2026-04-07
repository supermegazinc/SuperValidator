package com.supermegazinc.validator.example

import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.core.utils.failedValidatorOrNull
import com.supermegazinc.validator.core.utils.isValid
import com.supermegazinc.validator.validators.CommonStringValidators

fun inputExample() {

	//How validation data is stored in the backend
	//You save an initial value and a set of input validators
	data class InputData(
		val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
		val lastName: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
	)

	val inputData = InputData()

	//Check if a name is valid
	val nameIsValid = inputData.name.validators.isValid("Lucas")
	//Check which validation was failed - EmptyField
	val failedValidator = inputData.lastName.validators.failedValidatorOrNull("name")

}