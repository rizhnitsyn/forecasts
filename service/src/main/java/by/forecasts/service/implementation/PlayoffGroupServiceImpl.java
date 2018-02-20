package by.forecasts.service.implementation;

import by.forecasts.repositories.PlayoffGroupRepository;
import by.forecasts.service.PlayoffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayoffGroupServiceImpl implements PlayoffGroupService {

    private final PlayoffGroupRepository playoffGroupRepository;

    @Autowired
    public PlayoffGroupServiceImpl(PlayoffGroupRepository playoffGroupRepository) {
        this.playoffGroupRepository = playoffGroupRepository;
    }
}

