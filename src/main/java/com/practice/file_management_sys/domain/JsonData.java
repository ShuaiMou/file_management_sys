package com.practice.file_management_sys.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 功能：响应结果类
 *
 */
@Setter
@Getter
public class JsonData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code; // 状态码 0 表示成功，1表示处理中，-1表示失败
	private Object data; // 数据
	private String msg;// 描述

	public JsonData() {
	}

	public JsonData(Integer code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	// 成功
	public static JsonData buildSuccess() {
		return new JsonData(0, null, null);
	}

	// 成功，传入数据
	public static JsonData buildSuccess(Object data) {
		return new JsonData(0, data, null);
	}

	// 失败，传入描述信息
	public static JsonData buildError(String msg) {
		return new JsonData(-1, null, msg);
	}

	// 失败，传入描述信息,状态码
	public static JsonData buildError(String msg, Integer code) {
		return new JsonData(code, null, msg);
	}

	// 成功，传入数据,及描述信息
	public static JsonData buildSuccess(Object data, String msg) {
		return new JsonData(0, data, msg);
	}

	// 成功，传入数据,及状态码
	public static JsonData buildSuccess(Object data, int code) {
		return new JsonData(code, data, null);
	}

	@Override
	public String toString() {
		return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
				+ "]";
	}

}
