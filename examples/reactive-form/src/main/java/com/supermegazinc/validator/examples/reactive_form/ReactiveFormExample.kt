package com.supermegazinc.validator.examples.reactive_form

import com.supermegazinc.flow.core.utils.set
import com.supermegazinc.validator.core.InputValidators
import com.supermegazinc.validator.core.utils.InputValidatorBuilder
import com.supermegazinc.validator.core.utils.IntValidators
import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.reactive.utils.ValidatedIntReactive
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.reactive.utils.reactive
import com.supermegazinc.validator.reactive.utils.renderIfValid
import com.supermegazinc.validator.validators.CommonStringValidators

//Store the validators of every field
object BackendFormValidators {
	val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val lastName: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val age: IntValidators = InputValidators(emptySet())
}

data class BackendReactiveForm(
	val name: ValidatedStringReactive = BackendFormValidators.name.reactive(""),
	val lastName: ValidatedStringReactive = BackendFormValidators.lastName.reactive(""),
	val age: ValidatedIntReactive = BackendFormValidators.age.reactive(-1),
)

val LinusValidator = InputValidatorBuilder<String> { it == "Linus" }

//Edit the form
fun useForm(form: BackendReactiveForm) {
	//Modify the fields on runtime
	form.name.value.set("Linus")
	form.lastName.value.set("Torvalds")
	form.age.value.set(50)

	//Update the validators on runtime
	form.name.validators.update { it + LinusValidator }
}

//Use the render functions to get the final values if they meet the requirements
fun finishForm(form: BackendReactiveForm) {
	require(form.name.renderIfValid()!=null)
	require(form.lastName.renderIfValid()!=null)
	require(form.age.renderIfValid()!=null)
	println("Form is valid!")
}