package articleapp

import grails.testing.mixin.integration.Integration

import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class ArticleIndexSpec extends GebSpec {

    def setup() {
        Article.withTransaction {
            Article.deleteAll(Article.list())
        }
    }

    def cleanup() {
    }

    void "test the title is correct"() {
        when:
        go '/'

        then:"The title is correct"
        title == "Articles"
    }

    void "test the list header is correct"() {
        when:
        go '/'

        then:"The title is correct"
        $("h1", 0).text() == "Articles"
    }

    void "test articles are displayed on separate rows"() {
        setup:
        Article.withTransaction {
            new Article(title: "testArticle1").save(flush: true, failOnError: true)
            new Article(title: "testArticle2").save(flush: true, failOnError: true)
        }

        when:"There are more than one articles on the main page"
        // Go to the home page
        go '/'

        then:
        // There should be two table rows, with text matching the article titles
        $("tr").allElements().size() == 2
        $("tr", 0).text() == "testArticle1"
        $("tr", 1).text() == "testArticle2"
    }
}
