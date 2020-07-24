package model

class Document {
    var authors : ArrayList<String> = ArrayList()
    var contents : String = ""
    var datetime:String = ""
    var isbn:String=""
    var price:String=""
    var publisher:String=""
    var sale_price:String=""
    var status:String=""
    var thumbnail:String=""
    var title:String="  "
    var translators:ArrayList<String> = ArrayList()
    var url:String=""


    override fun toString(): String {
        return "Document(authors=$authors, contents='$contents', datetime='$datetime', isbn='$isbn', price='$price', publisher='$publisher', sale_price='$sale_price', status='$status', thumbnail='$thumbnail', title='$title', translators=$translators, url='$url')"
    }


}
