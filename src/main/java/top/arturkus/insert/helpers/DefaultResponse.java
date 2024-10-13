package top.arturkus.insert.helpers;

import java.util.Date;

public record DefaultResponse(Boolean success, String message, Date timestamp) {
}