dependencies {
    implementation(project(":application"))
    implementation(project(":domain"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
    runtimeOnly("org.postgresql:postgresql")
}
