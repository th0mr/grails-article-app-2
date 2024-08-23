package articleapp

import grails.testing.services.ServiceUnitTest
import grails.test.hibernate.HibernateSpec
import spock.lang.Specification

class ArticleServiceSpec extends HibernateSpec implements ServiceUnitTest<ArticleService>{

    def setup() {
        Article.deleteAll(Article.list())
    }

    def cleanup() {
    }

    void "list returns nothing when no articles are present"() {
        expect:
        service.list().size() == 0
    }

    void "list returns articles when articles are present"() {
        when:
        new Article(title:"testArticle1", body:"testArticle1Body").save(flush:true, failOnError:true)
        new Article(title:"testArticle2", body:"testArticle2Body").save(flush:true, failOnError:true)

        then:
        service.list().size() == 2
    }

    void "get() returns the correct article when multiple articles are present"() {
        when:
        Article a1 = new Article(title:"testArticle1", body:"testArticle1Body").save(flush:true, failOnError:true)
        Article a2 = new Article(title:"testArticle2", body:"testArticle2Body").save(flush:true, failOnError:true)
        Article a3 = new Article(title:"testArticle3", body:"testArticle3Body").save(flush:true, failOnError:true)

        then:
        service.get(a1.id).title == "testArticle1"
        service.get(a2.id).title == "testArticle2"
        service.get(a3.id).title == "testArticle3"
    }

    void "get() returns null when no article of that id exists"() {
        expect:
        service.get(1) == null
    }
}
