package com.opentok.android;

import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v4.view.PointerIconCompat;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.ConnectionResult;

public class OpentokError {
    protected ErrorCode errorCode;
    protected Domain errorDomain;
    protected String errorMessage;
    protected Exception exception;

    public enum Domain {
        SessionErrorDomain,
        PublisherErrorDomain,
        SubscriberErrorDomain
    }

    public enum ErrorCode {
        UnknownError(-1),
        AuthorizationFailure(1004),
        InvalidSessionId(1005),
        ConnectionFailed(PointerIconCompat.TYPE_CELL),
        NoMessagingServer(1503),
        ConnectionRefused(1023),
        SessionStateFailed(PointerIconCompat.TYPE_GRAB),
        P2PSessionMaxParticipants(1403),
        SessionConnectionTimeout(PointerIconCompat.TYPE_GRABBING),
        SessionInternalError(CastStatusCodes.AUTHENTICATION_FAILED),
        SessionInvalidSignalType(1461),
        SessionSignalDataTooLong(1413),
        SessionSignalTypeTooLong(1414),
        ConnectionDropped(1022),
        SessionDisconnected(PointerIconCompat.TYPE_ALIAS),
        PublisherInternalError(CastStatusCodes.AUTHENTICATION_FAILED),
        PublisherWebRTCError(1610),
        PublisherUnableToPublish(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED),
        PublisherUnexpectedPeerConnectionDisconnection(1710),
        PublisherCannotAccessCamera(1650),
        PublisherCameraAccessDenied(1670),
        ConnectionTimedOut(1542),
        SubscriberSessionDisconnected(1541),
        SubscriberWebRTCError(1600),
        SubscriberServerCannotFindStream(1604),
        SubscriberStreamLimitExceeded(1605),
        SubscriberInternalError(CastStatusCodes.AUTHENTICATION_FAILED),
        UnknownPublisherInstance(CastStatusCodes.NOT_ALLOWED),
        UnknownSubscriberInstance(CastStatusCodes.APPLICATION_NOT_FOUND),
        SessionNullOrInvalidParameter(PointerIconCompat.TYPE_COPY),
        VideoCaptureFailed(PathInterpolatorCompat.MAX_NUM_POINTS),
        CameraFailed(3010),
        VideoRenderFailed(4000),
        SessionSubscriberNotFound(1112),
        SessionPublisherNotFound(1113),
        PublisherTimeout(1541),
        SessionBlockedCountry(1026),
        SessionConnectionLimitExceeded(1027),
        SessionUnexpectedGetSessionInfoResponse(CastStatusCodes.INVALID_REQUEST),
        SessionIllegalState(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW);
        
        private int code;

        private ErrorCode(int code2) {
            this.code = code2;
        }

        public int getErrorCode() {
            return this.code;
        }

        public static ErrorCode fromTypeCode(int id) {
            for (ErrorCode code2 : values()) {
                if (code2.getErrorCode() == id) {
                    return code2;
                }
            }
            return UnknownError;
        }
    }

    public OpentokError(Domain errorDomain2, int errorCode2, String msg) {
        this.errorMessage = msg == null ? "(null description)" : msg;
        this.errorDomain = errorDomain2;
        this.errorCode = ErrorCode.fromTypeCode(errorCode2);
    }

    public OpentokError(Domain errorDomain2, int errorCode2, Exception exception2) {
        this.errorMessage = "(null description)";
        this.errorDomain = errorDomain2;
        this.errorCode = ErrorCode.fromTypeCode(errorCode2);
        this.exception = exception2;
    }

    public Domain getErrorDomain() {
        return this.errorDomain;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public Exception getException() {
        return this.exception;
    }
}
