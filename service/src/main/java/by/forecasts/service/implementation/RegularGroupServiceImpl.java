package by.forecasts.service.implementation;

import by.forecasts.repositories.RegularGroupRepository;
import by.forecasts.service.RegularGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegularGroupServiceImpl implements RegularGroupService {

    private final RegularGroupRepository regularGroupRepository;

    @Autowired
    public RegularGroupServiceImpl(RegularGroupRepository regularGroupRepository) {
        this.regularGroupRepository = regularGroupRepository;
    }
}
