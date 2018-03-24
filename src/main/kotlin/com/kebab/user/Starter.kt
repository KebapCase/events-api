package com.kebab.user

import org.springframework.boot.SpringApplication.run
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Class contains application-wide annotation to enable/disable functionality (core/provided by dependencies).
 */
@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement
class Starter : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder) = application.sources(Starter::class.java)!!
}

fun main(args: Array<String>) {
    run(Starter::class.java, *args)
}
