package by.forecasts.service.implementation;

import by.forecasts.dao.PlayoffGroupDao;
import by.forecasts.service.PlayoffGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayoffGroupServiceImpl implements PlayoffGroupService {

    private final PlayoffGroupDao playoffGroupDao;

    @Autowired
    public PlayoffGroupServiceImpl(PlayoffGroupDao playoffGroupDao) {
        this.playoffGroupDao = playoffGroupDao;
    }
}

