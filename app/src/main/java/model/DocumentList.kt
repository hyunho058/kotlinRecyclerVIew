package model

import com.google.gson.annotations.SerializedName

class DocumentList {
    @SerializedName("documents")
    var documents : ArrayList<Document> = ArrayList()
}