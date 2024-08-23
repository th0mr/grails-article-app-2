package articleapp

class ArticleController {

    ArticleService articleService

    def index() {
        respond articleService.list()
    }

    def show(Long id) {
        respond articleService.get(id)
    }
}
