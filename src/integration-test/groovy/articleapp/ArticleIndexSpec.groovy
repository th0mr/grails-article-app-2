package articleapp

import geb.navigator.Navigator
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
            new Article(title: "testArticle1", body:"testArticle1Body").save(flush: true, failOnError: true)
            new Article(title: "testArticle2", body:"testArticle2Body").save(flush: true, failOnError: true)
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

    void "test viewing an article"() {
        setup:
        Article.withTransaction {
            new Article(title: "testArticle1", body:"testArticle1Body").save(flush: true, failOnError: true)
        }

        when:"The home page is visited"
        go '/'

        then:"There should be an article with a view button"
        $("tr").allElements().size() == 1
        Navigator button = $("tr", 0).find("td", 1).find("input", 0, type: "submit")
        // Check the button was found
        button != null
        button.value() == "View"

        when:"The View button is clicked"
        button.click()

        then:"The new page displays the title and body of the article"
        // Page title matches the title of the article
        title == "testArticle1"
        // The body contains a header matching the title of the article
        $("body").find("h1").text() == "testArticle1"
        $("body").find("div", 0).text().contains("testArticle1Body")
    }
}
