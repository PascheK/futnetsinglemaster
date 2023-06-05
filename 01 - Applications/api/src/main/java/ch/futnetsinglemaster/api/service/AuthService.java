package ch.futnetsinglemaster.api.service;

import ch.futnetsinglemaster.api.beans.LoginRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;

public interface AuthService {
    ResultJSON login(LoginRequest user);
}
