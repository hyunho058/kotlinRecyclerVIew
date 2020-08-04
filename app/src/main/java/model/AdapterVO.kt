package model

class AdapterVO {
    lateinit var bookTitle : String
    lateinit var documentList: ArrayList<Document>
    var viewType : Int

    constructor(bookTitle: String, viewType: Int){
        this.bookTitle=bookTitle
        this.viewType=viewType
    }

    constructor(documentList: ArrayList<Document>, viewType: Int){
        this.documentList = documentList
        this.viewType = viewType
    }
}