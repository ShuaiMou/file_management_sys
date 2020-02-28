package com.practice.file_management_sys.enumClass;

public enum StateType {
    /**
     * 请求已成功，请求所希望的响应头或数据体将随此响应返回。实际的响应将取决于所使用的请求方法。在GET请求中，
     * 响应将包含与请求的资源相对应的实体。在POST请求中，响应将包含描述或操作结果的实体。
     */
    OK(200,"OK"),

    /***
     * 请求已经被实现，而且有一个新的资源已经依据请求的需要而建立，且其URI已经随Location头信息返回。
     */
    CREATED(201,"new resource is created"),


    /***
     * 服务器已接受请求，但尚未处理。最终该请求可能会也可能不会被执行，并且可能在处理发生时被禁止
     */
    ACCEPTED(202,"accept"),

    /***
     * 服务器成功处理了请求，没有返回任何内容
     */
    NO_CONTENT(204, "no content"),

    /**
     * 由于明显的客户端错误（例如，格式错误的请求语法，太大的大小，无效的请求消息或欺骗性路由请求），服务器不能或不会处理该请求
     */
    BAD_REQUEST(400,"bad request"),

    /**
     * 即用户没有必要的凭据。该状态码表示当前请求需要用户验证。该响应必须包含一个适用于被请求资源的WWW-Authenticate信息头用以询问用户信息。
     */
    UNAUTHORIZED(401,"unauthorized"),

    /**
     * 服务器已经理解请求，但是拒绝执行它。与401响应不同的是，身份验证并不能提供任何帮助，而且这个请求也不应该被重复提交。如果这不是一个HEAD请求，
     * 而且服务器希望能够讲清楚为何请求不能被执行，那么就应该在实体内描述拒绝的原因。当然服务器也可以返回一个404响应，假如它不希望让客户端获得任何信息。
     */
    FORBIDDEN(403,"forbidden"),

    /**
     * 请求失败，请求所希望得到的资源未被在服务器上发现，但允许用户的后续请求。
     * 没有信息能够告诉用户这个状况到底是暂时的还是永久的。假如服务器知道情况的话，应当使用410状态码来告知旧资源因为某些内部的配置机制问题，
     * 已经永久的不可用，而且没有任何可以跳转的地址。404这个状态码被广泛应用于当服务器不想揭示到底为何请求被拒绝或者没有其他适合的响应可用的情况下。
     */
    NOT_FOUND(404,"not found"),

    /**
     * 请求行中指定的请求方法不能被用于请求相应的资源。该响应必须返回一个Allow头信息用以表示出当前资源能够接受的请求方法的列表。
     */
    METHOD_NOT_ALLOWED(405,"method not allowed"),

    /**
     * 请求类型错误
     */
    UNSUPPORTED_MEDIA_TYPE(415,"unsupported media type"),

    /**
     * 服务器内部错误，无法完成请求
     */
    INTERNAL_SERVER_ERROR(500,"Internal Server Error");




    private int code;
    private String value = null;

    private StateType(int code,String value) {
        this.code = code;
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public int getCode() {
        return code;
    }
}
