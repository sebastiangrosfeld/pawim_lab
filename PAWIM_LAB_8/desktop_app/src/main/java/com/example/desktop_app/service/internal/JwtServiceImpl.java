package com.example.desktop_app.service.internal;

import com.example.desktop_app.service.JwtService;
import java.util.prefs.Preferences;

public class JwtServiceImpl implements JwtService {
    private final Preferences storage = Preferences.userRoot().node("com.example.desktop_app");
    @Override
    public void saveToken(String jwtToken) {
        storage.put("token", jwtToken);
    }

    @Override
    public String getJwtToken() {
        return storage.get("token", null);
    }

    @Override
    public void removeToken() {
        storage.remove("token");
    }
}
