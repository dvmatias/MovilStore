package com.matias.domain.base.exception

import android.net.sip.SipErrorCode

sealed class FailureType {
	class NetworkConnection(val errorCode: Int = 500) : FailureType()
	class ServerError(val errorCode: Int = 400) : FailureType()
	class LocalError(val errorCode: Int = 327) : FailureType()
}