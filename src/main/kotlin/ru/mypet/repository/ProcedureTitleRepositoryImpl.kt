package ru.mypet.repository

import ru.mypet.data.db.daos.ProcedureTitlesDAO
import ru.mypet.data.db.daos.ProcedureTitlesDAOImpl
import ru.mypet.models.ProcedureTitle
import ru.mypet.models.titlesParams.CreateProcedureTitleParams
import ru.mypet.utils.BaseResponse

class ProcedureTitleRepositoryImpl(
    private val prTitleDao: ProcedureTitlesDAO = ProcedureTitlesDAOImpl()
) : ProcedureTitleRepository {

    override suspend fun getAll(): BaseResponse<Any> {
        val titles = prTitleDao.getAll()
        return BaseResponse.SuccessResponse(data = titles)
    }

    override suspend fun updateProcedureTitle(params: ProcedureTitle): BaseResponse<Any> {
        prTitleDao.getById(params.id) ?: return BaseResponse.ErrorResponse(msg = "No such title")
        return if (prTitleDao.update(params)) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(msg = "Something went wrong")
        }
    }

    override suspend fun createProcedureTitle(params: CreateProcedureTitleParams): BaseResponse<Any> {
        val title = prTitleDao.insert(params)
        return if (title != null) {
            BaseResponse.SuccessResponse(data = title)
        } else {
            BaseResponse.ErrorResponse()
        }
    }
}