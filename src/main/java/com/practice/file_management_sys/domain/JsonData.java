package com.practice.file_management_sys.domain;

import com.practice.file_management_sys.enumClass.StateType;
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
public class JsonData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 数据
	 */
	private Object data;
	/**
	 * 描述
	 */
	private String msg;

    /**
     *  成功
     *
     * @return 状态码，消息
     */
	public static JsonData buildSuccess() {
		return new JsonData(StateType.OK.getCode(), null, StateType.OK.value());
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
