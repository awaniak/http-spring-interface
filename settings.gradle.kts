pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/milestone") }
		gradlePluginPortal()
	}
}
rootProject.name = "spring-http-interface-demo"
include("billing-service")
include("customer-service")