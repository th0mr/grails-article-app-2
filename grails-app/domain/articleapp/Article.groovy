package articleapp

class Article {

    String title
    String body

    String toString() { title }

    static constraints = {
        title blank: false
        body blank: false
    }
}
