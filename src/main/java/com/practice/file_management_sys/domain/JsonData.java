package com.practice.file_management_sys.domain;

import com.practice.file_management_sys.enumClass.StateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 功能：响应结果类
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "返回数据对象", description = "前后端交互的数据格式" )
public class JsonData implements Serializable {

	@ApiModelProperty(value = "状态码", dataType = "int", notes = "http响应请求状态码")
	private int code;

	@ApiModelProperty(value = "返回的数据", dataType = "Object", notes = "对返回数据的封装对象")
	private Object data;

	@ApiModelProperty(value = "状态描述", dataType = "String", notes = "对当前返回结果对具体描述")
	private String msg;

    /**
     *  成功
     *
     * @return 状态码，消息
     */
	public static JsonData buildSuccess() {
		return new JsonData(StateType.OK.getCode(), null, StateType.OK.value());
	}

	public static JsonData buildSuccess(int code, Object data, String msg) {
		return new JsonData(code, data, msg);
	}

	public static JsonData buildSuccess(int code, String msg) {
		return buildSuccess(code, null, msg);
	}

    /**
     * 成功，传入数据
     *
     * @return 状态码，消息，数据
     */
	public static JsonData buildSuccess(Object data) {
		return new JsonData(StateType.OK.getCode(), data, StateType.OK.value());
	}

    /**
     * 失败，传入描述信息,状态码
     *
     * @param msg 消息
     * @param code 状态码
     * @return 状态码，消息
     */
	public static JsonData buildError(Integer code, String msg) {
		return new JsonData(code, null, msg);
	}


	@Override
	public String toString() {
		return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
				+ "]";
	}

}
