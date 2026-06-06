package com.codewithsandip.ds.lint

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class MissingContentDescriptionDetectorTest {

    // Minimal stub matching the real CWSButton signature & package.
    private val cwsButtonStub = kotlin(
        """
        package com.codewithsandip.ds.component.button
        fun CWSButton(
            text: String,
            onClick: () -> Unit,
            contentDescription: String? = null,
        ) {}
        """,
    ).indented()

    @Test
    fun flagsButtonWithoutContentDescription() {
        lint()
            .files(
                cwsButtonStub,
                kotlin(
                    """
                    package test
                    import com.codewithsandip.ds.component.button.CWSButton
                    fun Screen() {
                        CWSButton(text = "Save", onClick = {})
                    }
                    """,
                ).indented(),
            )
            .issues(MissingContentDescriptionDetector.ISSUE)
            .run()
            .expectWarningCount(1)
    }

    @Test
    fun passesWhenContentDescriptionProvided() {
        lint()
            .files(
                cwsButtonStub,
                kotlin(
                    """
                    package test
                    import com.codewithsandip.ds.component.button.CWSButton
                    fun Screen() {
                        CWSButton(text = "Save", onClick = {}, contentDescription = "Save changes")
                    }
                    """,
                ).indented(),
            )
            .issues(MissingContentDescriptionDetector.ISSUE)
            .run()
            .expectClean()
    }
}
