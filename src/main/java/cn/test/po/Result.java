package cn.test.po;

import java.io.Serializable;

/**
 * 通用实体类
 * 
 * @param <T>
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 状态，默认是失败=false
	 */
	private boolean status = false;

	/**
	 * 错误码，默认是失败=99，成功=0
	 */
	private int errCode = 99;

	/**
	 * 错误信息
	 */
	private String errMsg = "";

	/**
	 * 返回结果实体
	 */
	private T resultData;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", errCode=" + errCode + ", errMsg=" + errMsg + ", resultData=" + resultData + "]";
	}
}
