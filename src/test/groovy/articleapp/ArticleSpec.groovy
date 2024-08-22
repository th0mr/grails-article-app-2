package articleapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ArticleSpec extends Specification implements DomainUnitTest<Article> {

    Article a = new Article(title:"testTitle")

    def setup() {
    }

    def cleanup() {
    }

    void "default article is valid"() {
        expect:
        a.validate()
        a.title == "testTitle"
    }


    void "title must not be blank"() {
        when:
        a.title = ""

        then:
        !a.validate()
        a.errors.getFieldError('title').code == 'blank'
    }
}
