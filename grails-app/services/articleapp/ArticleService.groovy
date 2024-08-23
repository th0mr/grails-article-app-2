package articleapp

import grails.gorm.transactions.Transactional

@Transactional
class ArticleService {

    List<Article> list() {
        return Article.list()
    }

    Article get(Long id) {
        return Article.get(id)
    }
}
