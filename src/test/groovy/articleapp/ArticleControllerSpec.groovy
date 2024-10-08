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
        Article testArticle1 = new Article(title:"testArticle1", body:"testArticle1Body")
        Article testArticle2 = new Article(title:"testArticle2", body:"testArticle2Body")
        controller.articleService = Mock(ArticleService) {
            1 * list() >> [testArticle1, testArticle2]
        }

        when:
        controller.index()

        then:
        model.articleList == [testArticle1, testArticle2]
    }

    void "Test show action returns the correct article when multiple articles are present"(){
        given:
        Article testArticle1 = new Article(title:"testArticle1", body:"testArticle1Body")
        Article testArticle2 = new Article(title:"testArticle2", body:"testArticle2Body")
        // Set up the mock to expect a call of get(id:1) and a call of get(id:2)
        controller.articleService = Mock(ArticleService) {
            1 * get(1) >> testArticle1
            1 * get(2) >> testArticle2
        }

        when:"id=1 is requested"
        controller.show(1)

        then:"the first article is returned"
        model.article == testArticle1

        when:"id=2 is requested"
        controller.show(2)

        then:"the second article is returned"
        model.article == testArticle2
    }
}
