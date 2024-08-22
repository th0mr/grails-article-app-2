package articleapp

class ArticleController {

    ArticleService articleService

    def index() {
        respond articleService.list()
    }
}
