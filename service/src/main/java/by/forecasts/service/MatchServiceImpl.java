package by.forecasts.service;

import by.forecasts.dao.MatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchDao matchDao;

    @Autowired
    public MatchServiceImpl(MatchDao matchDao) {
        this.matchDao = matchDao;
    }
}
