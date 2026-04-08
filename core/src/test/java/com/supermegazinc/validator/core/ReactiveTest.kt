package com.supermegazinc.validator.core

import com.supermegazinc.flow.core.utils.set
import com.supermegazinc.validator.core.utils.IntValidators
import com.supermegazinc.validator.core.utils.StringValidators
import com.supermegazinc.validator.core.utils.asInputValidators
import com.supermegazinc.validator.reactive.utils.ValidatedIntReactive
import com.supermegazinc.validator.reactive.utils.ValidatedStringReactive
import com.supermegazinc.validator.reactive.utils.isValidFlow
import com.supermegazinc.validator.reactive.utils.reactive
import com.supermegazinc.validator.reactive.utils.renderIfValid
import com.supermegazinc.validator.validators.CommonStringValidators
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

data class InputData(
	val name: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
	val lastName: StringValidators = setOf(CommonStringValidators.EmptyField).asInputValidators(),
	val age: IntValidators = IntValidators(emptySet()),
)

data class InputDataReactive(
	val name: ValidatedStringReactive,
	val lastName: ValidatedStringReactive,
	val age: ValidatedIntReactive
)

class ReactiveTest {
	@Test
	fun test(): Unit = runBlocking {
		val inputData = InputData()
		val inputReactive = InputDataReactive(
			name = inputData.name.reactive(""),
			lastName = inputData.lastName.reactive(""),
			age = inputData.age.reactive(-1)
		)
		val isFinished = listOf(
			inputReactive.name,
			inputReactive.lastName,
			inputReactive.age
		).isValidFlow()

		assert(!isFinished.first())

		inputReactive.name.value.set("Linus")
		inputReactive.lastName.value.set("Torvalds")
		inputReactive.age.value.set(50)

		assert(isFinished.first())
		assertEquals("Linus", inputReactive.name.renderIfValid())
		assertEquals("Torvalds", inputReactive.lastName.renderIfValid())
		assertEquals(50, inputReactive.age.renderIfValid())
	}
}
