
plugins {
    application
}

application {
    mainClass = "com.seegrid.demo.bootstrap.DemoApplication"
}

dependencies {
    implementation(project(":infrastructure"))
    implementation(project(":application"))
    implementation(project(":domain"))
}