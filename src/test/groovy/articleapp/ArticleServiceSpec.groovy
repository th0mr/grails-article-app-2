package articleapp

import grails.testing.services.ServiceUnitTest
import grails.test.hibernate.HibernateSpec
import spock.lang.Specification

class ArticleServiceSpec extends HibernateSpec implements ServiceUnitTest<ArticleService>{

    def setup() {
    }

    def cleanup() {
    }

    void "list returns nothing when no articles are present"() {
        expect:
        service.list().size() == 0
    }

    void "list returns articles when articles are present"() {
        when:
        new Article(title:"testArticle1").save()
        new Article(title:"testArticle2").save()

        then:
        service.list().size() == 2
    }
}
