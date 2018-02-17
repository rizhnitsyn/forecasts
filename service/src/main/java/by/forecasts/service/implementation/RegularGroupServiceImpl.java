package by.forecasts.service.implementation;

import by.forecasts.dao.RegularGroupDao;
import by.forecasts.service.RegularGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegularGroupServiceImpl implements RegularGroupService {

    private final RegularGroupDao regularGroupDao;

    @Autowired
    public RegularGroupServiceImpl(RegularGroupDao regularGroupDao) {
        this.regularGroupDao = regularGroupDao;
    }
}
