package org.code.http.exception;

import org.code.http.constant.ExceptionCode;

public class BusinessException extends Throwable {

	private static final long serialVersionUID = -5519288243296538690L;

	private int errorCode = 0;

	public BusinessException(ExceptionCode errorCode, String errorMsg) {
		super(errorCode.getErrorMessage() + " " + errorMsg);
		this.errorCode = errorCode.getErrorCode();
	}

	public BusinessException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public BusinessException(ExceptionCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode.getErrorCode();
	}

	public BusinessException(ExceptionCode errorCode, Throwable thrower) {
		super(errorCode.getErrorMessage(), thrower);
		this.errorCode = errorCode.getErrorCode();
	}

	@Override
	public String getMessage() {
		if (this.errorCode < 1) {
			return super.getMessage();
		}
		return "error code :" + errorCode + ", error message:"
				+ super.getMessage();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
