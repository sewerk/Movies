mapOf(
    "kotlinVersion" to "1.3.71",
    "junitVersion" to "4.13",
    "appcompatVersion" to "1.1.0",
    "androidCoreVersion" to "1.2.0",
    "constraintlayoutVersion" to "1.1.3",
    "lifecycleVersion" to "2.2.0",
    "androidTestVersion" to "1.1.1",
    "espressoVersion" to "3.2.0"
).forEach { (name, version) ->
    project.extra.set(name, version)
}

