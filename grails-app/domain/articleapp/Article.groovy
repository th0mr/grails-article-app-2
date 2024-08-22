package articleapp

class Article {

    String title

    String toString() { title }

    static constraints = {
        title blank: false
    }
}
