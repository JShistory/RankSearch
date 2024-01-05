package com.example.Foods.response;

import com.example.Foods.riotApi.entity.SpellType;
import lombok.Getter;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@Getter
public enum ErrorCode {
    Null(0,"?"),
    OK(200, "성공"),
    Save(201, "저장 성공"),
    Bad_Request(400, "잘못된 접근입니다."),
    Unauthorized(401,"인증되지 않은 사용자입니다."),
    Forbidden(403,"API 오류입니다."),
    Data_not_found(404,"존재하지 않는 소환사입니다."),
    Method_not_allowed(405,"해당 요청 메소드는 제한합니다."),
    Unsupported_media_type(415,"요청하신 포맷은 지원하지 않습니다."),
    Rate_limit_exceeded(429,"호출량을 초과하였습니다."),
    Internal_server_error(500,"서버 내부에 문제가 발생하였습니다."),
    Bad_gateway(502,"서버에 문제가 발생했습니다."),
    Service_unavailable(503,"서버에 문제가 발생했습니다."),
    Gateway_timeout(504,"시간 초과되었습니다.");




    private int httpStatusCode;
    private String message;
    ErrorCode(int httpStatusCode, String message){
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public static ErrorCode fromHttpStatsCode(int httpStatusCode) {
        for (ErrorCode value : ErrorCode.values()) {
            if (value.httpStatusCode == httpStatusCode) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid httpStatusCode value: " + httpStatusCode);
    }

}
