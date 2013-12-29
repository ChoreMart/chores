package com.choremart
import org.springframework.security.access.annotation.Secured

class HomeController {
    @Secured(['ROLE_SUPER_ADMIN'])
    def index() {
        render(view: "index")
    }
}
