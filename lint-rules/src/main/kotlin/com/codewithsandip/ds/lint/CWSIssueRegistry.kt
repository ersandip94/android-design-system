package com.codewithsandip.ds.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

/** Registry exposing the CWS Design System's custom lint checks. */
class CWSIssueRegistry : IssueRegistry() {

    override val issues: List<Issue> = listOf(
        MissingContentDescriptionDetector.ISSUE,
    )

    override val api: Int = CURRENT_API

    override val vendor: Vendor = Vendor(
        vendorName = "CodeWithSandip",
        feedbackUrl = "https://codewithsandip.com",
        identifier = "com.codewithsandip:ds-core",
    )
}
