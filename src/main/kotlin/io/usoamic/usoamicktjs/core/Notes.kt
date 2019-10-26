package io.usoamic.usoamicktjs.core

import io.usoamic.usoamicktjs.model.Note
import io.usoamic.web3kt.core.contract.response.CallResponse
import io.usoamic.web3kt.core.contract.response.ExecuteResponse

interface Notes : Ideas {
    @JsName("addPublicNote")
    fun addPublicNote(content: String): ExecuteResponse

    @JsName("addUnlistedNote")
    fun addUnlistedNote(content: String): ExecuteResponse

    @JsName("getNumberOfPublicNotes")
    fun getNumberOfPublicNotes(): CallResponse<String>

    @JsName("getNumberOfNotesByAuthor")
    fun getNumberOfNotesByAuthor(address: String): CallResponse<String>

    @JsName("getLastNoteId")
    fun getLastNoteId(): CallResponse<String>

    @JsName("getLastNoteIdByAddress")
    fun getLastNoteIdByAddress(address: String): CallResponse<String>

    @JsName("getNoteByAuthor")
    fun getNoteByAuthor(author: String, noteId: String): CallResponse<Note>

    @JsName("getNote")
    fun getNote(noteRefId: String): CallResponse<Note>
}