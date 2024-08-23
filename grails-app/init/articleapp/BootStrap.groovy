package articleapp

class BootStrap {

    def init = { servletContext ->
        new Article(title: "Recipe for the best egg fried rice!", body: "cook your eggs!").save(failOnError: true)
        new Article(title: "Recipe for the worse egg fried rice...", body: "dont cook your eggs!").save(failOnError: true)
    }
    def destroy = {
    }
}
