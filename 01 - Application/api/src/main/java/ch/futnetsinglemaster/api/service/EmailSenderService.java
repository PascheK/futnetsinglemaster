package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.ResultJSON;

import java.util.Map;

public interface EmailSenderService {
    ResultJSON sendSimpleEmail(String mailTo, String subject, Map<String, Object> model);
}
