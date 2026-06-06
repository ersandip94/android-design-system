package com.codewithsandip.ds.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

/**
 * Flags calls to CWS components that expose a `contentDescription` parameter but omit it,
 * nudging callers toward labeled, screen-reader-friendly UI.
 */
class MissingContentDescriptionDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames(): List<String> =
        listOf("CWSButton", "CWSTextField", "CWSCard")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        // Only our design system components, not unrelated functions that share a name.
        val owner = method.containingClass?.qualifiedName ?: return
        if (!owner.startsWith("com.codewithsandip.ds.component")) return

        val parameters = method.parameterList.parameters
        val index = parameters.indexOfFirst { it.name == "contentDescription" }
        if (index == -1) return // this component doesn't take a contentDescription

        if (node.getArgumentForParameter(index) == null) {
            context.report(
                issue = ISSUE,
                scope = node,
                location = context.getLocation(node),
                message = "Missing `contentDescription` — provide one so screen readers can " +
                    "announce this ${method.name}.",
            )
        }
    }

    companion object {
        @JvmField
        val ISSUE: Issue = Issue.create(
            id = "CWSMissingContentDescription",
            briefDescription = "CWS component is missing a contentDescription",
            explanation = """
                CodeWithSandip components such as CWSButton accept a `contentDescription` for \
                assistive technologies. Omitting it can leave the element unlabeled or \
                ambiguously labeled for screen-reader users. Provide a concise, meaningful \
                description (or rely on visible text only when that text fully conveys the action).
            """,
            category = Category.A11Y,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                MissingContentDescriptionDetector::class.java,
                Scope.JAVA_FILE_SCOPE,
            ),
        )
    }
}
