package com.supermegazinc.validator.examples.compose_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.supermegazinc.flow.compose.utils.getValue
import com.supermegazinc.flow.compose.utils.setValue
import com.supermegazinc.validator.compose.utils.ValidatedIntCompose
import com.supermegazinc.validator.compose.utils.ValidatedStringCompose
import com.supermegazinc.validator.compose.utils.compose
import com.supermegazinc.validator.core.utils.IntValidator
import com.supermegazinc.validator.core.utils.IntValidators
import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.reactive.utils.ValidatedIntReactive
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.reactive.utils.isValidFlow
import com.supermegazinc.validator.reactive.utils.reactive
import com.supermegazinc.validator.validators.CommonStringValidators

class GreaterValidator(private val than: Int) : IntValidator {
	override fun validate(input: Int): Boolean = input > than
}


//Store the validators of every field
object BackendFormValidators {
	val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val lastName: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators()
	val age: IntValidators = setOf(GreaterValidator(18)).asInputValidators()
}

data class BackendReactiveForm(
	val name: ValidatedStringReactive = BackendFormValidators.name.reactive(""),
	val lastName: ValidatedStringReactive = BackendFormValidators.lastName.reactive(""),
	val age: ValidatedIntReactive = BackendFormValidators.age.reactive(-1),
) {
	val isFinished = listOf(
		name,
		lastName,
		age
	)
}

data class BackendComposeForm(
	val name: ValidatedStringCompose,
	val lastName: ValidatedStringCompose,
	val age: ValidatedIntCompose,
)

@Composable
fun ExampleScreen(form: BackendReactiveForm) {

	val isFinished by listOf(
		form.name,
		form.lastName,
		form.age
	).isValidFlow().collectAsStateWithLifecycle(false)

	val composeForm = BackendComposeForm(
		name = form.name.compose(),
		lastName = form.lastName.compose(),
		age = form.age.compose()
	)

	var name by composeForm.name.inputState
	var lastName by composeForm.lastName.inputState
	var age by composeForm.age.inputState

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		TextField(
			placeholder = { Text("Name") },
			value = name,
			onValueChange = { name = it },
			isError = composeForm.name.failedValidator != null
		)
		TextField(
			placeholder = { Text("Last Name") },
			value = lastName,
			onValueChange = { lastName = it },
			isError = composeForm.lastName.failedValidator != null
		)
		TextField(
			placeholder = { Text("Age") },
			value = age.takeIf { it >= 0 }?.toString() ?: "",
			onValueChange = { age = it.toIntOrNull() ?: -1 },
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.NumberPassword
			),
			isError = composeForm.age.failedValidator != null
		)
		Button(
			enabled = isFinished,
			onClick = {}
		) {
			Text("Finish")
		}
	}

}

@Composable
@Preview
fun ExampleScreenPreview() {

	val formReactive by remember {
		mutableStateOf(BackendReactiveForm())
	}

	Surface() {
		ExampleScreen(formReactive)
	}

}