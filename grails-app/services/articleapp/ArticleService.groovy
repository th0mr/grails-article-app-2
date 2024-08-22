package articleapp

import grails.gorm.transactions.Transactional

@Transactional
class ArticleService {

    List<Article> list() {
        return Article.list()
    }
}
