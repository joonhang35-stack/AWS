package com.bcs.zsg.apiresponse.vo;

public class ApiResponseVO<T> {

	private ApiStatus status;

	private T data;

	private ApiErrorVO error;

	private Long timestamp;

	public ApiResponseVO() {
	}

	public static <T> ApiResponseVO<T> success(T data) {

		ApiResponseVO<T> response = new ApiResponseVO<>();

		response.setStatus(ApiStatus.SUCCESS);
		response.setData(data);
		response.setTimestamp(System.currentTimeMillis());

		return response;
	}

	public static <T> ApiResponseVO<T> error(String code, String message) {

		ApiResponseVO<T> response = new ApiResponseVO<>();

		response.setStatus(ApiStatus.ERROR);
		response.setError(new ApiErrorVO(code, message));
		response.setTimestamp(System.currentTimeMillis());

		return response;
	}
	
	public boolean isSuccess() {
        return ApiStatus.SUCCESS.equals(this.status);
    }

    public boolean isError() {
        return ApiStatus.ERROR.equals(this.status);
    }

	public ApiStatus getStatus() {
		return status;
	}

	public void setStatus(ApiStatus status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ApiErrorVO getError() {
		return error;
	}

	public void setError(ApiErrorVO error) {
		this.error = error;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}