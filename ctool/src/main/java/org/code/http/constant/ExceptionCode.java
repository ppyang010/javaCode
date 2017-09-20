package org.code.http.constant;

public enum ExceptionCode {
	
	FTP_UPLOAD_ERROR(10001,"ftp UP load error!"),
	NO_CONTENT_INFO(10002,"can not query content info!"),
	VIDEO_FILE_NOT_EXISTS(10003,"source video file path is empty!"),
	FILE_COPY_FAIL(10004,"copy file fail!"),
	CREATE_DIRECTORY_TO_VMS_STORE_ARRAY_ERROR(10005,"create directory to vms store array error!"),
	HTTP_RESPONSE_CODE_IS_NOT_OK(10006,"http response code is not 200!"),
	SEND_HTTP_REQUEST_ERROR(10007,"send http request is error!"),
	VMS_PUBLISH_NOTIFY_PROCESS_ERROR(10008,"vms process publish notify error!"),
	USER_BATCH_REGISTER_ERROR(10009,"user batch register error"),
	USER_BATCH_REGISTERLOG_ERROR(10010,"add to the registerlog ERROR"),
	USER_BATCH_GET_SMSCONTENT_ERROR(10011,"get the smscontent error from the sysconfig"),
	USER_BATCH_GET_DNS_ERROR(10012,"get the MTN project DNS error"),
	USER_BATCH_GET_SHORT_LINK_ERROR(10013,"get the shortLink error from the shorLink project"),
	QUERY_EFFECTIVE_RECORDS_ERROR(10014,""),
	UPDATE_EFFECTIVE_RECORDS_ERROR(10015,"");
	
	private int  errorCode;
	private String errorMessage;
	
	ExceptionCode(int  errorCode, String errorMessage)
	{
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	

}
