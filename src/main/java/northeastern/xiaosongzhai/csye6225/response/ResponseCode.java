package northeastern.xiaosongzhai.csye6225.response;

import lombok.Getter;

/**
 * @Author: Xiaosong Zhai
 * @date: 2023/10/1 17:26
 * @Description: define json response code
 */
@Getter
public enum ResponseCode {

    SUCCESS(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    Forbidden(403),
    SERVICE_UNAVAILABLE(503);


    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }
}
