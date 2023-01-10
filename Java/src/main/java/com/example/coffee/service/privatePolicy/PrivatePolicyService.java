package com.example.coffee.service.privatePolicy;

import com.example.coffee.entity.PrivatePolicy;

public interface PrivatePolicyService {
    void save(String descriptionPrivatePolicy);
    PrivatePolicy getPrivatePolicy();
}
