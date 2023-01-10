package com.example.coffee.service.privatePolicy;

import com.example.coffee.entity.PrivatePolicy;
import com.example.coffee.repository.PrivatePolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrivatePolicyServiceImpl implements PrivatePolicyService {
    private final PrivatePolicyRepository privatePolicyRepository;

    @Override
    @Transactional
    public void save(String descriptionPrivatePolicy) {
        Optional<PrivatePolicy> privatePolicy = privatePolicyRepository.findByIdPrivatePolicyNotNull();
        if (privatePolicy.isPresent()) {
            privatePolicy.get().setDescription(descriptionPrivatePolicy);
        } else {
            privatePolicyRepository.save(PrivatePolicy.builder()
                    .description(descriptionPrivatePolicy)
                    .build()
            );
        }
    }

    @Override
    public PrivatePolicy getPrivatePolicy() {
        Optional<PrivatePolicy> privatePolicy = privatePolicyRepository.findByIdPrivatePolicyNotNull();
        return privatePolicy.orElse(new PrivatePolicy());
    }
}
