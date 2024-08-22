package articleapp

class BootStrap {

    def init = { servletContext ->
        new Article(title: "Recipe for the best egg fried rice!").save()
        new Article(title: "Recipe for the worse egg fried rice...").save()
    }
    def destroy = {
    }
}
