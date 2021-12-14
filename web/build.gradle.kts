dependencies {
    // NOTE web は infra に依存しない。
    // しかし、SpringBootApplication が infra モジュールのコンポーネントを DI できないのでやむなく。
    implementation(project(":infra"))

    implementation(project(":application"))
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
}
