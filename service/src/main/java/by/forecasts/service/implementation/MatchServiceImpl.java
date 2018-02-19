package by.forecasts.service.implementation;

import by.forecasts.dao.MatchDao;
import by.forecasts.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchDao matchDao;

    @Autowired
    public MatchServiceImpl(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
