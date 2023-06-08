package com.franktran.domain;

public record NotificationRequest(Integer toCustomerId,
                                  String toCustomerName,
                                  String message) {
}
