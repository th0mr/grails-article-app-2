package articleapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ArticleControllerSpec extends Specification implements ControllerUnitTest<ArticleController> {

    def setup() {
    }

    def cleanup() {
    }

    void "Test index action returns nothing when no articles are present"() {
        given:
        Article testArticle2 = new Article(title:"testArticle2")
        controller.articleService = Mock(ArticleService) {
            1 * list() >> []
        }

        when:
        controller.index()

        then:
        // the controller returns a null articleList when the return from
        // articleService.list() is empty rather than an empty list
        model.articleList == null
    }

    void "Test index action returns articles when articles are present"() {
        given:
        Article testArticle1 = new Article(title:"testArticle1")
        Article testArticle2 = new Article(title:"testArticle2")
        controller.articleService = Mock(ArticleService) {
            1 * list() >> [testArticle1, testArticle2]
        }

        when:
        controller.index()

        then:
        model.articleList == [testArticle1, testArticle2]
    }
}
