package com.demo.publicserviceapi.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StatusConstants {

    @Getter
    @AllArgsConstructor
    public enum HttpConstants {

        SUCCESS("0", "Success"),
        EMAIL_IS_REQUIRED("TEMP31300", "email is required"),
        GENDER_IS_INVALID("TEMP31301", "gender is invalid"),
        DATE_OF_BIRTH_IS_REQUIRED("TEMP31302", "date_of_birth is required"),
        DATE_OF_BIRTH_IS_INVALID("TEMP31303", "date_of_birth is invalid"),
        CONSENT_FLAG_IS_REQUIRED("TEMP31304", "consent_flag is required"),
        NEWSLETTER_ID_IS_REQUIRED("TEMP31305", "newsletter_id is required"),
        ACTION_IS_REQUIRED("TEMP31306", "action is required"),
        ACTION_IS_INVALID("TEMP31307", "action is invalid"),


        EXTERNAL_SERVICE_ERROR("TEMP31992", "external service error"),
        DUPLICATE_EMAIL_ERROR("TEMP31993", "email is duplicate"),
        METHOD_NOT_ALLOWED("TEMP31994", "Method not allowed"),
        JSON_DECODING_ERROR("TEMP31995", "JSON Decoding error"),
        NO_MATCHING_HANDLER("TEMP31996", "No matching handler"),
        NOT_FOUND("TEMP31997", "Not found"),
        BAD_REQUEST("TEMP31998", "Bad request"),
        INTERNAL_SERVER_ERROR("TEMP31999", "Internal server error");

        private final String code;
        private final String desc;

    }

}
