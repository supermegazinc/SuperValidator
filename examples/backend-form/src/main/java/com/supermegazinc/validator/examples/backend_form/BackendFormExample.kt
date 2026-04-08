package com.supermegazinc.validator.examples.backend_form

import com.supermegazinc.validator.core.InputValidators
import com.supermegazinc.validator.core.utils.IntValidators
import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.core.utils.isValid
import com.supermegazinc.validator.validators.CommonStringValidators

//Store the validators of every field
object BackendFormValidators {
	val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val lastName: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val age: IntValidators = InputValidators(emptySet())
}

data class BackendForm(
	val name: String,
	val lastName: String,
	val age: Int,
)

//Use the validators to validate every field
fun finishForm(form: BackendForm) {
	require(BackendFormValidators.name.validators.isValid(form.name))
	require(BackendFormValidators.lastName.validators.isValid(form.lastName))
	require(BackendFormValidators.age.validators.isValid(form.age))
	println("Form is valid!")
}